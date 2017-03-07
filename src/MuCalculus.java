/**
 * Created by s1309096 on 06/03/17.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MuCalculus {


                    /* RULE1: P or not P -> valid */
    public static boolean Rule1(List<formula> form) {
        int p = 0;
        int np = 0;
        for(formula f : form){
            if(f.toString().startsWith("P"))
                p = 1;
            if(f.toString().startsWith("NP"))
                np = 1;
        }
        if(p == 1 && np ==1)
            return true;
        return false;
    }


                    /* Rule2: F or not F -> form */
    public static boolean Rule2(List<formula> form) {
        for(formula f : form)
            if(f.toString().startsWith("N"))
                for(formula g : form)
                    if(g.toString().equals(f.toString().substring(1)))
                        return true;
        return false;
    }


                    /* Rule3: F or G --> F, G */
    public static List<formula> Rule3(List<formula> form) {
        List<formula> newList = new ArrayList<>(form);
        for(formula f: form)
            if(f.toString().startsWith("or")) {
                    /* first split the formulas properly */
                int paratheses = 0;
                int commaIndex;
                for(commaIndex = 0; commaIndex<f.toString().length(); commaIndex++){
                    if(f.toString().charAt(commaIndex) == '(')
                        paratheses++;
                    else if(f.toString().charAt(commaIndex) == ')')
                        paratheses--;
                    else if(f.toString().charAt(commaIndex) == ',' && paratheses ==1)
                        break;
                }
                    /* Then expand OR into two formulas */
                formula f1 = f; f1.form = f.toString().substring(3, commaIndex-1);
                formula f2 = f; f2.form = f.toString().substring(commaIndex + 2);
                newList.remove(f);
                newList.add(f1);
                newList.add(f2);
                break;
            }
        return newList;
    }


                    /* Rule4: F and G --> two trees */
    public static boolean Rule4(List<formula> form) {
        return false;
    }


                    /* Rule5: Modal Rule */
    public static List<formula> Rule5(List<formula> form) {
        List<formula> newList = new ArrayList<>(form);
        for(formula f : form)
            if(f.toString().startsWith("[a]")) {
                newList.clear();
                formula f1 = f;
                f1.form = f.toString().substring(3);
                newList.add(f1);
                for (formula g : form)
                    if (g.toString().startsWith("<a>")) {
                        formula g1 = g;
                        g1.form = g.toString().substring(3);
                        newList.add(g1);
                    }
                break;
            }
            return newList;
    }


                    /* Rule6: Unfolding least fixed point */
    public static List<formula> Rule6(List<formula> form) {
        List<formula> newList = new ArrayList<>(form);
        for(formula f: form)
            if(f.toString().startsWith("m")) {
                formula f1 = f; f1.form = f.toString().substring(3);
                newList.remove(f);
                newList.add(f1);
                break;
            }
        return newList;
    }


                    /* add new name when unfolding greatest fixed point */
    public static formula addName(formula f) {
        formula f1 = f;
        f1.form = f.toString().substring(3);
        if(f.names == null) {
            f1.nameNumber = 1;
            f1.names = Character.toString(f.toString().toLowerCase().charAt(1)) + f1.nameNumber;
        }
        else {
            f.nameNumber++;
            f1.names = f1.names + f.toString().toLowerCase().charAt(1) + f1.nameNumber;
        }
        return f1;
    }

                    /* Rule7: Unfolding greatest fixed point */
    public static List<formula> Rule7(List<formula> form) {
        List<formula> newList = new ArrayList<>(form);
        for(formula f : form)
            if(f.toString().startsWith("n")) {
                formula f1 = addName(f);
                newList.remove(f);
                newList.add(f1);
                break;
            }
        return newList;
    }


                    /* Structural Rule 1: Thin rule */
    public static List<formula> structuralRule1(List<formula> form) {
        List<formula> newList = new ArrayList<>(form);
        for(formula f : form)
            for(formula g : form)
                if(f.toString().equals(g.toString()) && f != g) {
                    if(f.names.startsWith(g.names)) {
                        newList.remove(g);
                        break;
                    }
                    else if(g.names.startsWith(f.names)) {
                        newList.remove(f);
                        break;
                    }
                }
        return newList;
    }

                    /* Find prefix to reset on */
    public static String prefix(String s1, String s2, List<formula> form) {
        if(s1 == null || s2 == null)
            return null;
        int index = 0; int flag = 0;
        while(index < s1.length() && index < s2.length() && flag == 0) {
            index = index + 2;
            if(s1.startsWith(s2.substring(0, index)))
                for(formula f : form)
                    if(f.names == null || !f.names.contains(s2.substring(index - 2, index))) // z not in Gamma
                        flag = 1;
        }
        if(flag == 1)
            return s2.substring(0, index);
        else
            return null;
    }


                    /* Perform reset on all formulas that contain prefix*/
    public static boolean doReset(List<formula> form, String prefix) {
        for(formula f: form) {
            if(f.names != null)
                if(f.names.startsWith(prefix) && f.names.length() > prefix.length())
                    f.names = prefix;
        }
        return true;
    }


                    /* Structural Rule 2: Reset rule */
    public static List<formula> structuralRule2(List<formula> form) {
        List<formula> newList = new ArrayList<>(form);
        if(newList.size() == 1)
            if(newList.get(0).names != null && form.get(0).names.length()>2) {
                newList.get(0).names = newList.get(0).names.substring(0, 2);
                return newList;
            }
        for(formula f : form)
            for(formula g : form) {
                String prefix = prefix(f.names, g.names, form);
                if(prefix != null) {
                    doReset(form, prefix);
                    break;
                }
            }
        return newList;
    }

}
