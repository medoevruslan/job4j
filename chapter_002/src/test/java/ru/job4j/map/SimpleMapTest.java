package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class SimpleMapTest {

    private SimpleMap<String, String> map;

    @Before
    public void testInit() {
        map = new SimpleMap<>();
        map.insert(null, "zero");
        map.insert("1", "first");
        map.insert("2", "second");
        map.insert("3", "third");
        map.insert("4", "fourth");
    }

    @Test
    public void whenMapHasSize4() {
        assertThat(map.getSize(), is(5));
    }

    @Test
    public void whenGetByKeyShouldBeElementValue() {
        assertThat(map.get("3"), is("third"));
        assertThat(map.get(null), is("zero"));
    }

    @Test
    public void whenDeleteTwoElementsSizeShouldBe() {
        map.delete("1");
        map.delete("2");
        assertThat(map.getSize(), is(3));
    }

    @Test
    public void whenDeleteSameElementTwoTimesShouldBeFalse() {
        map.delete(null);
        assertThat(map.delete(null), is(false));
    }

    @Test
    public void whenInsertSameElementKeyThanFalse() {
        assertThat(map.insert(null, "same"), is(false));
        assertThat(map.insert("1", "same"), is(false));
    }

    @Test
    public void whenInsertMoreThanDefaultElementsTableShouldResize() {
        Iterator<SimpleMap.Element<String, String>> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().value, is("zero"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().value, is("first"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().value, is("second"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().value, is("third"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().value, is("fourth"));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        Iterator<SimpleMap.Element<String, String>> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next().value(), is("zero"));
        assertThat(it.next().value(), is("first"));
        assertThat(it.next().value(), is("second"));
        assertThat(it.next().value(), is("third"));
        assertThat(it.next().value(), is("fourth"));
    }

    @Test (expected = NoSuchElementException.class)
    public void testWillBeThrowExceptionBecauseNextOnEndOfTheList() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.insert("1", "first");
        map.insert("2", "second");
        Iterator<SimpleMap.Element<String, String>> it = map.iterator();
        assertThat(it.next().value(), is("first"));
        assertThat(it.next().getKey(), is("2"));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testShouldThrowExceptionBecauseModCountDoNotEquals() {
        Iterator<SimpleMap.Element<String, String>> it = map.iterator();
        it.next();
        map.insert("5", "firth");
        it.next();
    }
}