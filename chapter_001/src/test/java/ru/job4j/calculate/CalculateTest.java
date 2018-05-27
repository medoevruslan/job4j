package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Medoev Ruslan (mr.r.m3@icloud.com)
* @version $Id$
* @since 0.1
*/

public class CalculateTest {
  /**
  *Test echo
  */
  @Test
  public void whenTakeNameThenTreeEchoPlusName() {
    String input = "Medoev Ruslan";
    String expect = "Echo, echo, echo : Medoev Ruslan";
    Calculate calc = new Calculate();
    String result = calc.echo(input);
    assertThat(result, is(expect));
}
}
