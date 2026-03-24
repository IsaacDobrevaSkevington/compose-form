package com.idscodelabs.compose_form.core.controller.submission

import com.idscodelabs.compose_form.form.core.controller.add
import com.idscodelabs.compose_form.form.core.exceptions.FormSubmissionFailedError
import com.idscodelabs.compose_form.helpers.TestModel
import com.idscodelabs.compose_form.helpers.randomProperty
import com.idscodelabs.compose_form.helpers.testFormBox
import com.idscodelabs.compose_form.helpers.testFormController
import com.idscodelabs.compose_form.validators.NotEmptyValidator
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class SubmissionSynchronousTests {
    @Test
    fun `GIVEN The form controller has a valid value WHEN submitForModel is called THEN the result is returned`() =
        runTest {
            val formController =
                testFormController {
                    add(testFormBox("Some value"), randomProperty())
                }
            val result = formController.submitForModel()

            assertEquals(TestModel("Some value"), result)

            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller has an invalid value WHEN submitForModel is called THEN an error is thrown with the form boxes AND no cause`() =
        runTest {
            val box = testFormBox("", NotEmptyValidator())
            val formController =
                testFormController {
                    add(box, randomProperty())
                }

            val throwable =
                assertFailsWith(FormSubmissionFailedError::class) {
                    formController.submitForModel()
                }

            assertNull(throwable.cause)
            assertEquals(listOf(box), throwable.boxes)

            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller validation throws an error WHEN submitForModel is called THEN an error is thrown with the cause as the validation error AND an empty list of boxes`() =
        runTest {
            val error = RuntimeException("Some error")
            val failingBox = testFormBox("", { _, _ -> throw error })
            val formController =
                testFormController {
                    add(failingBox, randomProperty())
                }
            val result =
                assertFailsWith(FormSubmissionFailedError::class) {
                    formController.submitForModel()
                }

            assertEquals(error, result.cause)
            assertEquals(emptyList(), result.boxes)

            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller has a valid value WHEN submitForModelOrNull is called THEN the result is returned`() =
        runTest {
            val formController =
                testFormController {
                    add(testFormBox("Some value"), randomProperty())
                }
            val result = formController.submitForModelOrNull()

            assertEquals(TestModel("Some value"), result)

            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller has an invalid value WHEN submitForModelOrNull is called THEN null is returned`() =
        runTest {
            val box = testFormBox("", NotEmptyValidator())
            val formController =
                testFormController {
                    add(box, randomProperty())
                }

            val result = formController.submitForModelOrNull()

            assertNull(result)

            formController.clearForm()
        }

    @Test
    fun `GIVEN The form controller validation throws an error WHEN submitForModelOrNull is called THEN null is returned`() =
        runTest {
            val error = RuntimeException("Some error")
            val failingBox = testFormBox("", { _, _ -> throw error })
            val formController =
                testFormController {
                    add(failingBox, randomProperty())
                }
            val result = formController.submitForModelOrNull()

            assertNull(result)

            formController.clearForm()
        }
}
