package com.idscodelabs.compose_form.core.controller.submission

import com.idscodelabs.compose_form.form.core.controller.add
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.helpers.TestModel
import com.idscodelabs.compose_form.helpers.randomProperty
import com.idscodelabs.compose_form.helpers.testFormBox
import com.idscodelabs.compose_form.helpers.testFormController
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import com.idscodelabs.mock_function.functions.MockFunction1
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class SubmissionResultTests {
    @Test
    fun `GIVEN The form controller has a valid value WHEN submit is called THEN the result is the first parameter of the success callback`() =
        runTest {
            val successFunction = MockFunction1<TestModel>()
            val formController =
                testFormController {
                    add(testFormBox("Some value"), randomProperty())
                }
            formController.submit().onSuccess(successFunction)

            successFunction.verify {
                onlyCall {
                    argument isEqualTo TestModel("Some value")
                }
            }
            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller has a valid value WHEN submit is called THEN the failure callback is not called`() =
        runTest {
            val failureFunction = MockFunction1<List<FormBox<*, *>>>()
            val formController =
                testFormController {
                    add(testFormBox("Some value"), randomProperty())
                }
            formController.submit().onFailure(failureFunction)

            failureFunction.verify {
                wasNotCalled()
            }
            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller has a valid value WHEN submit is called THEN the error callback is not called`() =
        runTest {
            val errorFunction = MockFunction1<Throwable>()
            val formController =
                testFormController {
                    add(testFormBox("Some value"), randomProperty())
                }
            formController.submit().onError(errorFunction)

            errorFunction.verify {
                wasNotCalled()
            }
            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller has an invalid value WHEN submit is called THEN the failing boxes are the first parameter of the failure callback`() =
        runTest {
            val failureFunction = MockFunction1<List<FormBox<*, *>>>()
            val failingBox = testFormBox("", NotEmptyValidator())
            val formController =
                testFormController {
                    add(failingBox, randomProperty())
                }
            formController.submit().onFailure(failureFunction)

            failureFunction.verify {
                onlyCall {
                    argument isEqualTo listOf(failingBox)
                }
            }
            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller has an invalid value WHEN submit is called THEN the success callback is not called`() =
        runTest {
            val successFunction = MockFunction1<TestModel>()
            val failingBox = testFormBox("", NotEmptyValidator())
            val formController =
                testFormController {
                    add(failingBox, randomProperty())
                }
            formController.submit().onSuccess(successFunction)

            successFunction.verify {
                wasNotCalled()
            }
            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller has an invalid value WHEN submit is called THEN the error callback is not called`() =
        runTest {
            val errorFunction = MockFunction1<Throwable>()
            val failingBox = testFormBox("", NotEmptyValidator())
            val formController =
                testFormController {
                    add(failingBox, randomProperty())
                }
            formController.submit().onError(errorFunction)

            errorFunction.verify {
                wasNotCalled()
            }
            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller validation throws an error WHEN submit is called THEN the error is the first parameter of the error callback`() =
        runTest {
            val errorFunction = MockFunction1<Throwable>()
            val error = RuntimeException("Some error")
            val failingBox = testFormBox("", { _, _ -> throw error })
            val formController =
                testFormController {
                    add(failingBox, randomProperty())
                }
            formController.submit().onError(errorFunction)

            errorFunction.verify {
                onlyCall {
                    argument isEqualTo error
                }
            }
            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller validation throws an error WHEN submit is called THEN the success callback is not called`() =
        runTest {
            val successFunction = MockFunction1<TestModel>()
            val error = RuntimeException("Some error")
            val failingBox = testFormBox("", { _, _ -> throw error })
            val formController =
                testFormController {
                    add(failingBox, randomProperty())
                }
            formController.submit().onSuccess(successFunction)

            successFunction.verify {
                wasNotCalled()
            }
            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller validation throws an error WHEN submit is called THEN the failure callback is not called`() =
        runTest {
            val failureFunction = MockFunction1<List<FormBox<*, *>>>()
            val error = RuntimeException("Some error")
            val failingBox = testFormBox("", { _, _ -> throw error })
            val formController =
                testFormController {
                    add(failingBox, randomProperty())
                }
            formController.submit().onFailure(failureFunction)

            failureFunction.verify {
                wasNotCalled()
            }
            formController.clearForm()
        }
}
