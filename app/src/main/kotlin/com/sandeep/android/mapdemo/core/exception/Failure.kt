
package com.sandeep.android.mapdemo.core.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object JsonParsingError : Failure()
    object UnknownValueError : Failure()
    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
}
