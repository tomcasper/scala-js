/*
 * Scala.js (https://www.scala-js.org/)
 *
 * Copyright EPFL.
 *
 * Licensed under Apache License 2.0
 * (https://www.apache.org/licenses/LICENSE-2.0).
 *
 * See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 */

package org.scalajs.junit

import java.lang.annotation.Annotation

import scala.scalajs.reflect.annotation._

@EnableReflectiveInstantiation
trait JUnitTestBootstrapper {
  def metadata(): JUnitClassMetadata
  def newInstance(): AnyRef
  def invoke(methodName: String): Unit
  def invoke(instance: AnyRef, methodName: String): Unit
}

final class JUnitMethodMetadata(val name: String, annotations: List[Annotation]) {

  def hasTestAnnotation: Boolean =
    annotations.exists(_.isInstanceOf[org.junit.Test])

  def hasBeforeAnnotation: Boolean =
    annotations.exists(_.isInstanceOf[org.junit.Before])

  def hasAfterAnnotation: Boolean =
    annotations.exists(_.isInstanceOf[org.junit.After])

  def hasBeforeClassAnnotation: Boolean =
    annotations.exists(_.isInstanceOf[org.junit.BeforeClass])

  def hasAfterClassAnnotation: Boolean =
    annotations.exists(_.isInstanceOf[org.junit.AfterClass])

  def getTestAnnotation: Option[org.junit.Test] =
    annotations.collectFirst { case test: org.junit.Test => test }

  def getIgnoreAnnotation: Option[org.junit.Ignore] =
    annotations.collectFirst { case ign: org.junit.Ignore => ign }
}

final class JUnitClassMetadata(classAnnotations: List[Annotation],
    moduleAnnotations: List[Annotation], classMethods: List[JUnitMethodMetadata],
    moduleMethods: List[JUnitMethodMetadata]) {

  def testMethods: List[JUnitMethodMetadata] =
    classMethods.filter(_.hasTestAnnotation)

  def beforeMethod: List[JUnitMethodMetadata] =
    classMethods.filter(_.hasBeforeAnnotation)

  def afterMethod: List[JUnitMethodMetadata] =
    classMethods.filter(_.hasAfterAnnotation)

  def beforeClassMethod: List[JUnitMethodMetadata] =
    moduleMethods.filter(_.hasBeforeClassAnnotation)

  def afterClassMethod: List[JUnitMethodMetadata] =
    moduleMethods.filter(_.hasAfterClassAnnotation)
}
