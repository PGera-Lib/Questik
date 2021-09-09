package xyz.gmfatoom.questik.ui.base

interface EventHandler<E> {
    fun obtainEvent(event:E)
}