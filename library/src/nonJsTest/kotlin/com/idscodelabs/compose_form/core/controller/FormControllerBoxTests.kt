package com.idscodelabs.compose_form.core.controller

import androidx.compose.ui.test.ExperimentalTestApi
import com.idscodelabs.compose_form.form.core.controller.add
import com.idscodelabs.compose_form.helpers.mostRecent
import com.idscodelabs.compose_form.helpers.randomProperty
import com.idscodelabs.compose_form.helpers.testFormBox
import com.idscodelabs.compose_form.helpers.testFormController
import kotlinx.coroutines.test.runTest
import kotlin.test.*

@OptIn(ExperimentalTestApi::class)
class FormControllerBoxTests {
    @Test
    fun `GIVEN the form controller has no boxes WHEN a box is added THEN the field snapshot can be accessed`() =
        runTest {
            val property = randomProperty()
            val formController = testFormController()

            val formBox = testFormBox()
            formController.add(formBox, property)

            assertEquals(formBox, formController.fieldSnapshot(property))

            formController.clearForm()
        }

    @Test
    fun `GIVEN A box flow for a field not present in the form controller has been obtained WHEN a box is added THEN the field is emitted to the flow`() =
        runTest {
            val property = randomProperty()
            val formController = testFormController()

            val boxFlow = formController.field(property)

            val formBox = testFormBox()
            formController.add(formBox, property)

            assertEquals(formBox, mostRecent(boxFlow))

            formController.clearForm()
        }

    @Test
    fun `GIVEN the form controller has no boxes, WHEN a box is added THEN calling field provides the form box`() =
        runTest {
            val property = randomProperty()
            val formController = testFormController()

            val formBox = testFormBox()
            formController.add(formBox, property)

            val boxFlow = formController.field(property)

            assertEquals(formBox, mostRecent(boxFlow))

            formController.clearForm()
        }

    @Test
    fun `GIVEN the form controller has a box WHEN the clear function is called THEN the box is removed`() =
        runTest {
            val property = randomProperty()
            val formController =
                testFormController {
                    add(testFormBox(), property)
                }

            val fieldFlow = formController.field(property)

            val boxFlow =
                formController.state.boxFlows
                    .toList()
                    .first()
                    .second
            val observerJob =
                formController.state.observerJobs
                    .toList()
                    .first()
                    .second

            formController.clearForm()

            assertEquals(0, formController.state.boxes.size)
            assertEquals(0, formController.state.observerJobs.size)
            assertEquals(1, formController.state.boxFlows.size)
            assertNull(mostRecent(boxFlow))
            assertNull(mostRecent(fieldFlow))
            assertTrue(observerJob.isCancelled)
        }

    @Test
    fun `GIVEN the form controller has 2 boxes WHEN the clear function is called with one box THEN the box is removed and the other box remains`() =
        runTest {
            val property1 = randomProperty()
            val property2 = randomProperty()
            val field1 = testFormBox()
            val field2 = testFormBox()
            val formController =
                testFormController {
                    add(field1, property1)
                    add(field2, property2)
                }

            val fieldFlow1 = formController.field(property1)
            val fieldFlow2 = formController.field(property2)

            val boxFlow1 = formController.state.boxFlows[property1.name]!!
            val observerJob1 = formController.state.observerJobs[property1.name]!!

            val boxFlow2 = formController.state.boxFlows[property2.name]!!
            val observerJob2 = formController.state.observerJobs[property2.name]!!

            formController.clearForm(property1)

            assertEquals(1, formController.state.boxes.size)
            assertEquals(
                property2.name,
                formController.state.boxes
                    .toList()
                    .first()
                    .first,
            )
            assertEquals(1, formController.state.observerJobs.size)
            assertEquals(2, formController.state.boxFlows.size)
            assertNull(mostRecent(boxFlow1))
            assertNull(mostRecent(fieldFlow1))
            assertTrue(observerJob1.isCancelled)

            assertEquals(field2, mostRecent(boxFlow2))
            assertEquals(field2, mostRecent(fieldFlow2))
            assertFalse(observerJob2.isCancelled)

            formController.clearForm()
        }
}
