package com.avsystem.scex
package util

import org.slf4j.{Logger, LoggerFactory}

import scala.reflect.{ClassTag, classTag}

/**
 * Created: 06-12-2013
 * Author: ghik
 */
trait LoggingUtils {

  protected case class LazyLogger(underlying: Logger) {
    def trace(msg: => String, cause: Throwable = null): Unit = {
      if (underlying.isTraceEnabled) {
        underlying.trace(msg, cause)
      }
    }

    def debug(msg: => String, cause: Throwable = null): Unit = {
      if (underlying.isDebugEnabled) {
        underlying.debug(msg, cause)
      }
    }

    def info(msg: => String, cause: Throwable = null): Unit = {
      if (underlying.isInfoEnabled) {
        underlying.info(msg, cause)
      }
    }

    def warn(msg: => String, cause: Throwable = null): Unit = {
      if (underlying.isWarnEnabled) {
        underlying.warn(msg, cause)
      }
    }

    def error(msg: => String, cause: Throwable = null): Unit = {
      if (underlying.isErrorEnabled) {
        underlying.error(msg, cause)
      }
    }
  }

  protected def createLogger[T: ClassTag] =
    LazyLogger(LoggerFactory.getLogger(classTag[T].runtimeClass))
}
