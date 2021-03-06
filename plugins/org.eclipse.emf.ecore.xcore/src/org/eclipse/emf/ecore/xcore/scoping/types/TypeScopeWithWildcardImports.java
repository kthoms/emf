/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.ecore.xcore.scoping.types;

import java.util.List;

import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.xtext.AbstractTypeScope;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.util.Strings;

import com.google.common.collect.Iterables;

public class TypeScopeWithWildcardImports extends AbstractXcoreScope
{
  private final ImportNormalizer[][] imports;

  private final AbstractTypeScope typeScope;

  private final AbstractXcoreScope parent;

  public TypeScopeWithWildcardImports(ImportNormalizer[][] imports, AbstractTypeScope typeScope)
  {
    this.imports = imports;
    this.typeScope = typeScope;
    this.parent = null;
  }

  public TypeScopeWithWildcardImports(List<ImportNormalizer> imports, AbstractTypeScope typeScope, AbstractXcoreScope parent)
  {
    this.imports = new ImportNormalizer[][] { imports.toArray(new ImportNormalizer[imports.size()]) };
    this.typeScope = typeScope;
    this.parent = parent;
  }

  @Override
  public IEObjectDescription getSingleElement(QualifiedName name)
  {
    for (int i = 0; i < imports.length; i++)
    {
      ImportNormalizer[] chunk = imports[i];
      IEObjectDescription result = null;
      for (int j = 0; j < chunk.length; j++)
      {
        ImportNormalizer normalizer = chunk[j];
        QualifiedName resolvedName = normalizer.resolve(name);
        if (resolvedName != null)
        {
          IEObjectDescription candidate = typeScope.getSingleElement(resolvedName, true);
          if (candidate != null)
          {
            if (result == null)
            {
              result = candidate;
            }
            else
            {
              return null;
            }
          }
        }
      }
      if (result != null)
      {
        return result;
      }
    }
    return getSingleElementFromParent(name);
  }

  protected IEObjectDescription getSingleElementFromParent(QualifiedName name)
  {
    return parent != null ? parent.getSingleElement(name) : typeScope.getSingleElement(name);
  }

  @Override
  protected void doGetElements(JvmType type, List<IEObjectDescription> result)
  {
    if (type instanceof JvmDeclaredType)
    {
      JvmDeclaredType declaredType = (JvmDeclaredType) type;
      String packageName = declaredType.getPackageName();
      if (!Strings.isEmpty(packageName))
      {
        int packageNameEnd = packageName.length() + 1;
        QualifiedName qualifiedPackageName = QualifiedName.create(Strings.split(packageName, '.'));
        int qualifiedPackageNameSegmentCount = qualifiedPackageName.getSegmentCount();
        QualifiedName withDot = null;
        String withDollar = null;
        for (int i = 0; i < imports.length; i++)
        {
          ImportNormalizer[] chunk = imports[i];
          for (int j = 0; j < chunk.length; j++)
          {
            ImportNormalizer normalizer = chunk[j];
            QualifiedName namespacePrefix = normalizer.getImportedNamespacePrefix();
            if (namespacePrefix.equals(qualifiedPackageName))
            {
              if (withDot == null)
              {
                withDot = QualifiedName.create(Strings.split(type.getQualifiedName('.'), '.'));
                withDollar = type.eContainer() instanceof JvmType ? type.getQualifiedName('$').substring(packageNameEnd) : null;
              }
              result.add(EObjectDescription.create(withDot.skipFirst(qualifiedPackageNameSegmentCount), type));
              if (withDollar != null)
              {
                result.add(EObjectDescription.create(withDollar, type));
              }
            }
          }
        }
      }
      if (parent != null)
      {
        parent.doGetElements(type, result);
      }
      else
      {
        Iterables.addAll(result, typeScope.getElements(type));
      }
    }
  }
}