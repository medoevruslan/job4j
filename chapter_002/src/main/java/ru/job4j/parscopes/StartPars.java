package ru.job4j.parscopes;

import java.util.LinkedList;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class StartPars {

    public void init(String input) {
        LinkedList<Brackets> list = new LinkedList<>();
        Stacker stacker = new Stacker();
        if (!stacker.checkAndParse(input, list )) {
            System.out.println("Invalid brackets");
        } else {
            for (Brackets br : list) {
                System.out.println(br.getKind() + " - open: " + br.start + " close: " + br.end);
            }
        }
    }
}