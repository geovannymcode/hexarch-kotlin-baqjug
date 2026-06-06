package com.baqjug.payments.infrastructure.adapter.`in`.ui

import com.baqjug.payments.application.port.`in`.ProcessPaymentUseCase
import com.baqjug.payments.domain.model.Money
import com.baqjug.payments.domain.model.Payment
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.BigDecimalField
import com.vaadin.flow.router.Route

@Route("")
class PaymentView(
    private val useCase: ProcessPaymentUseCase
) : VerticalLayout() {

    private val amountField = BigDecimalField("Monto a cobrar")
    private val grid = Grid(Payment::class.java)

    init {
        val payButton = Button("Cobrar") {
            val payment = useCase.process(Payment(amount = Money(amountField.value)))
            Notification.show("Pago ${payment.status}")
            refresh()
        }

        add(amountField, payButton, grid)
        configureGrid()
        refresh()
    }

    private fun configureGrid() {
        grid.setColumns()
        grid.addColumn { it.id.value }.setHeader("ID")
        grid.addColumn { it.amount.value }.setHeader("Monto")
        grid.addColumn { it.status.name }.setHeader("Estado")
    }

    private fun refresh() {
        grid.setItems(useCase.findAll())
    }
}