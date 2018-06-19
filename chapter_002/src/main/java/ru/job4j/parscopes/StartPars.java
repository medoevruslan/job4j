package ru.job4j.parscopes;

import java.util.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */

public class StartPars {
   private ArrayList<Brackets> list = new ArrayList<>();
   private Stacker stacker = new Stacker();

    public void init(String input) {
        if (!this.stacker.checkAndParse(input, this.list)) {
            System.out.println("Invalid brackets");
        } else {
            System.out.println("String to parse - " + input +"\nresult : \n");
            this.list.sort(new Comparator<Brackets>() {
                @Override
                public int compare(Brackets first, Brackets second) {
                    return first.getKind().compareTo(second.getKind());
                }
            });
            for (Brackets br : this.list) {
                System.out.println(br.getKind() + " - open: " + br.start + " close: " + br.end);
            }
        }
    }
}