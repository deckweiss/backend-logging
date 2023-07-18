package at.deckweiss.logging

class LogParam(private val paramName: String, private val paramValue: Any?) {
    override fun toString(): String = """$paramName="${paramValue.toString()}""""
}