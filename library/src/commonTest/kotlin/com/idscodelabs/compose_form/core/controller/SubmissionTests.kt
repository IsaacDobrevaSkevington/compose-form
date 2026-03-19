package com.idscodelabs.compose_form.core.controller

import com.idscodelabs.compose_form.form.core.controller.add
import com.idscodelabs.compose_form.helpers.*
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class SubmissionTests {

    @Test
    fun `GIVEN The form controller has a valid value WHEN submit is called THEN the result is returned`() = runTest{
        val submitFunction = MockFunction1<TestModel>()
        val formController = testFormController {
            add(testFormBox("Some value"), randomProperty())
        }
        formController.submit(onSuccess = submitFunction)

        submitFunction.verify {
            wasCalledExactly(1)
            callAt(0){
                argument(0) isEqualTo TestModel("Some value")
            }
        }
        formController.clearForm()
    }
}