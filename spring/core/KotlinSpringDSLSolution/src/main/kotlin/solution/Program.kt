package solution

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.AbstractApplicationContext
import javax.swing.JFrame

object Program {
	@JvmStatic
	fun main(args: Array<String>) {
		buildContext().use { context ->
			val gui = context.getBean("gui", JFrame::class.java)
			gui.isVisible = true
		}
	}

	private fun buildContext(): AbstractApplicationContext {
		val context = AnnotationConfigApplicationContext()
		val currentPackageName: String = Program::class.java.getPackage().name
		context.scan(currentPackageName)
		context.refresh()
		return context
	}
}
