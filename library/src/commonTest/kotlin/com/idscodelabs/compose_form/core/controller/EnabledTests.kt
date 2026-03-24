package com.idscodelabs.compose_form.core.controller

import com.idscodelabs.compose_form.form.model.FormState
import com.idscodelabs.compose_form.helpers.testFormController
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EnabledTests {
    @Test
    fun `GIVEN a form controller THEN the form state is enabled`() =
        runTest {
            val controller = testFormController()

            assertEquals(controller.state.formStateFlow.value, FormState.Enabled)
        }

    @Test
    fun `GIVEN a form controller WHEN setEnabled is called with false THEN the form state is disabled`() =
        runTest {
            val controller = testFormController()

            controller.setEnabled(false)

            assertEquals(controller.state.formStateFlow.value, FormState.Disabled)
        }

    @Test
    fun `GIVEN a disabled form controller WHEN setEnabled is called with true THEN the form state is enabled`() =
        runTest {
            val controller =
                testFormController {
                    setEnabled(false)
                }

            controller.setEnabled(true)

            assertEquals(controller.state.formStateFlow.value, FormState.Enabled)
        }

    @Test
    fun `GIVEN a submitting form controller WHEN setEnabled is called with true THEN the form state is submitting AND the previous state is enabled`() =
        runTest {
            val controller =
                testFormController {
                    state.formStateFlow.value = FormState.Submitting(FormState.Disabled)
                }

            controller.setEnabled(true)

            assertEquals(controller.state.formStateFlow.value, FormState.Submitting(FormState.Enabled))
        }

    @Test
    fun `GIVEN a submitting form controller WHEN setEnabled is called with false THEN the form state is submitting AND the previous state is disabled`() =
        runTest {
            val controller =
                testFormController {
                    state.formStateFlow.value = FormState.Submitting(FormState.Enabled)
                }

            controller.setEnabled(false)

            assertEquals(controller.state.formStateFlow.value, FormState.Submitting(FormState.Disabled))
        }
}
