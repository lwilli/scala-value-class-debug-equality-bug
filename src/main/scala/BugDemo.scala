/**
 * Example of a Scala Value Class equality bug in IntelliJ's debugger evaluation
 *
 * To reproduce, set a breakpoint (after `value` is instantiated) and use the debugger to evaluate the equality checks.
 * You will see the debugger incorrectly evaluates the equality checks to `false`.
 * (The printed output for each check is `true`.)
 */
object BugDemo extends App {
  class Value(val underlying: String) extends AnyVal

  // It doesn't matter how we instantiate this val or what type we annotate it with--all combinations show the same behavior.
  val value = new Value("same")

  // The debugger evaluates this equality check incorrectly
  println(value == value)

  // Interestingly, the debugger evaluates these correctly...
  println(new Value("same") == new Value("same"))
  println((new Value("same"): Value) == new Value("same"))
  // ...but not these
  println(new Value("same") == (new Value("same"): Value))
  println((new Value("same"): Value) == (new Value("same"): Value))
}
