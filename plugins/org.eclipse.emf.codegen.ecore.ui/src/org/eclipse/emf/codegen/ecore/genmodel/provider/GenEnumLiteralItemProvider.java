/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;


/**
 * This is the item provider adapter for a {@link org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenEnumLiteralItemProvider
  extends GenBaseItemProvider
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenEnumLiteralItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addEcoreEnumLiteralPropertyDescriptor(object);
      addDocumentationPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Ecore Enum Literal feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEcoreEnumLiteralPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenEnumLiteral_ecoreEnumLiteral_feature"),
         getString("_UI_GenEnumLiteral_ecoreEnumLiteral_description"),
         GenModelPackage.Literals.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL,
         false,
         false,
         false,
         null,
         getString("_UI_EcorePropertyCategory"),
         null));
  }

  /**
   * This adds a property descriptor for the Documentation feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  protected void addDocumentationPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_GenEnumLiteral_documentation_feature"),
         getString("_UI_GenEnumLiteral_documentation_description"),
         GenModelPackage.Literals.GEN_ENUM_LITERAL__DOCUMENTATION,
         true,
         true,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         getString("_UI_ModelPropertyCategory"),
         null));
  }

  /**
   */
  @Override
  public Object getImage(Object object)
  {
    return new UnderlayedImage(getResourceLocator().getImage("full/obj16/EEnumLiteral"));
  }

  /**
   * This returns the label text for the adapted class.
   */
  @Override
  public String getText(Object object)
  {
    GenEnumLiteral genEnumLiteral = (GenEnumLiteral)object;
    EEnumLiteral ecoreEnumLiteral = genEnumLiteral.getEcoreEnumLiteral();
    return ecoreEnumLiteral == null || ecoreEnumLiteral.getName() == null ? "" : ecoreEnumLiteral.getName() + " = " + ecoreEnumLiteral.getValue();
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(GenEnumLiteral.class))
    {
      case GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL:
      case GenModelPackage.GEN_ENUM_LITERAL__DOCUMENTATION:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
    }
    super.notifyChanged(notification);
  }

}
