package com.avsystem.scex
package compiler.xmlfriendly

import org.scalatest.FunSuite

/**
  * Created: 23-04-2014
  * Author: ghik
  */
class XmlFriendlyTranslatorTest extends FunSuite {

  import com.avsystem.scex.compiler.xmlfriendly.XmlFriendlyTranslator.translate

  test("variables test") {
    assert(" _vars.lol" == translate("#lol", template = false).result)
  }

  test("variables in template test") {
    assert("fuu #lol $$lol ${fuu} haha ${ _vars.dafuq + 5} ss" ==
      translate("fuu #lol $lol ${fuu} haha ${#dafuq + 5} ss", template = true).result)
  }

  test("negated variable test") {
    assert("! _vars.costam" == translate("!#costam", template = false).result)
  }

  test("operator translation test") {
    assert("a || b &&  c" == translate("a or b and c", template = false).result)
  }

  test("operator translation in template test") {
    assert("a or ${b &&  c} or d" == translate("a or ${b and c} or d", template = true).result)
  }

  test("large template test") {
    val str = "lol" * 10000
    assert(str == translate(str, template = true).result)
  }

  test("large template test 2") {
    val str = "lol${}" * 10000
    assert(str == translate(str, template = true).result)
  }

  test("backticked variable test") {
    assert("${ _vars.`type`}" == translate("${#`type`}").result)
  }

  test("keyword variable test") {
    assert("${ _vars.`type`}" == translate("${#type}").result)
  }

  test("unused scala keyword test") {
    assert("${`type`}" == translate("${type}").result)
    assert("${costam.`type`}" == translate("${costam.type}").result)
  }
}
