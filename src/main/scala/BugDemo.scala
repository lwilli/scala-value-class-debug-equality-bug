/**
 * Example of a Scala Value Class equality bug in IntelliJ's debugger evaluation
 *
 * To reproduce, set a breakpoint (after `value` is instantiated) and use the debugger to evaluate the equality checks.
 * You will see the debugger incorrectly evaluates the equality checks to `false`.
 * (The printed output for each check is `true`.)
 */
object BugDemo extends App {
  class Value(val underlying: String) extends AnyVal
  type ValueAlias = Value

  // It doesn't matter how we instantiate this val or what type we annotate it with--all combinations show the same behavior.
  val value = new Value("same")
  // The debugger evaluates these equality checks incorrectly
  println(value == value)
  println((value: Value) == value)
  println(value == (value: Value))
  println((value: Value) == (value: Value))
  println((value: ValueAlias) == value)
  println(value == (value: ValueAlias))
  println((value: ValueAlias) == (value: ValueAlias))
  
  // Interestingly, the debugger evaluates these correctly...
  println(new Value("same") == new Value("same"))
  println((new Value("same"): ValueAlias) == new Value("same"))
  // ...but not these
  println(new Value("same") == (new Value("same"): ValueAlias))
  println((new Value("same"): ValueAlias) == (new Value("same"): ValueAlias))
  println((new Value("same"): Value) == (new Value("same"): ValueAlias))
  println((new Value("same"): ValueAlias) == (new Value("same"): Value))
  println((new Value("same"): Value) == (new Value("same"): Value))
}
