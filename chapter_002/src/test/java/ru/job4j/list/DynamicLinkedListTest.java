package ru.job4j.list;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */


public class DynamicLinkedListTest {

    DynamicLinkedList<Integer> list;


    @Before
    public void beforeTest() {
        list = new DynamicLinkedList<>();
        this.list.add(1);
        this.list.add(2);
        this.list.add(3);
        this.list.add(4);
    }

    @Test
    public void whenDeleteTwoElementsElementsThan3() {
        list.delete();
        assertThat(list.delete(), is(3));
    }

    @Test
    public void whenNextSequentialInvokationIs() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        Iterator<Integer> it = list.iterator();
        MatcherAssert.assertThat(it.hasNext(), Matchers.is(true));
        MatcherAssert.assertThat(it.hasNext(), Matchers.is(true));
        MatcherAssert.assertThat(it.next(), Matchers.is(4));
        MatcherAssert.assertThat(it.next(), Matchers.is(3));
        MatcherAssert.assertThat(it.next(), Matchers.is(2));
        MatcherAssert.assertThat(it.next(), Matchers.is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void testWillBeThrowExceptionBecauseNextOnEndOfTheList() {
        DynamicLinkedList<Integer> linkedList = new DynamicLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        Iterator<Integer> it = linkedList.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testShouldThrowExceptionBecauseModCountDoNotEquals() {
        Iterator<Integer> it = list.iterator();
        it.next();
        list.add(5);
        it.next();
    }
}