package org.eclipse.example.library;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model
 */
public interface Writer extends EObject
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String copyright = "This is my code.";

  /**
   * @model
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.example.library.Writer#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * @model type="Book" opposite="author"
   */
  EList getBooks();
}