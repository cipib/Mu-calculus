/**
 * Created by s1309096 on 06/03/17.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MuCalculus {
    private static int tautology = 0;
    private static List<Integer> resetIndexes = new ArrayList<>();
    private static int nodeIndex =0;

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


                    /* Copy elements of a list into another */
    public static List<formula> copy(List<formula> form) {
        List<formula> newList = new ArrayList<formula>();
        for(formula f : form){
            formula copy = deepCopy(f);
            newList.add(copy);
        }
        return newList;
    }


                        /* deep copy */
    public static formula deepCopy(formula input){
        formula copy = new formula(input.toString());
        copy.nameNumber = input.nameNumber;
        copy.names = input.names;
        return copy;
    }

                    /* Create new formula, but retain the names */
    public static formula create(formula f, String form) {
        formula f1 = new formula(form);
        f1.names = f.names;
        f1. nameNumber = f.nameNumber;
        return f1;
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
                formula f1 = create(f, f.toString().substring(3, commaIndex -1));
                formula f2 = create(f, f.toString().substring(commaIndex + 2, f.toString().length() - paratheses));
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
                formula f1 = create(f, f.toString().substring(3));
                newList.add(f1);
                for (formula g : form)
                    if (g.toString().startsWith("<a>")) {
                        formula g1 = create(g, g.toString().substring(3));

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
                formula f1 = create(f, f.toString().substring(3));
                newList.remove(f);
                newList.add(f1);
                break;
            }
        return newList;
    }


                    /* add new name when unfolding greatest fixed point */
    public static formula addName(formula f) {
        formula f1 = create(f, f.toString());
        f1.form = f.toString().substring(3);
        if(f.names == null) {
            f1.nameNumber = 1;
            f1.names = f.abbrev.toLowerCase() + f1.nameNumber;
        }
        else {
            f1.nameNumber = f.names.length()/2 + 1;
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
        List<formula> newList = copy(form);
        if(newList.size() == 1)
            if(newList.get(0).names != null && newList.get(0).names.length() > 2) {
                newList.get(0).names = newList.get(0).names.substring(0, 2);
                return newList;
            }
        for(formula f : newList)
            for(formula g : newList) {
                String prefix = prefix(f.names, g.names, form);
                if(prefix != null) {
                    doReset(newList, prefix);
                    break;
                }
            }
        return newList;
    }


                    /* Find the name of the fixed point to assign as abbreviation */
    public static char findFirstVariable(String str) {
        String variables = "TWXYZ";
        for(int i =0; i< str.length(); i++) {
            if(variables.indexOf(str.charAt(i)) != -1)
                return str.charAt(i);
        }
        return 'A';
    }


                    /* Find abbreviations of formulas */
    public static List<formula> abbreviations(File input) throws IOException {
        List<formula> abbrev = new ArrayList<formula>();
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            int k = 0;
            while ((line = br.readLine()) != null) {
                for (int i = line.length() - 1; i >= 0; i--) {
                    k = line.length();
                    if ((line.charAt(i) == 'm' || line.charAt(i) == 'n') && line.charAt(i+1) != 'd') {
                        formula f = new formula(line.substring(i, k));
                        f.abbrev = Character.toString(findFirstVariable(line.substring(i, k)));
                        line = line.substring(0, i) + f.abbrev;
                        abbrev.add(f);
                        k = i;
                    }
                }
            }
        }
        Collections.reverse(abbrev);
        return abbrev;
    }


                    /* Index ob the abbreviation */
    public static int abbrevIndex(List<formula> form, String x) {
        int index  = -1;
        int i =-1;
        for(formula f :form) {
            i++;
            if (f.toString().equals(x))
                return i;
        }
        return index;
    }



                    /* Apply Rules */
    public static List<formula> applyRules(List<formula> formulas, List<formula> abbrev) {
        if(Rule1(formulas) || Rule2(formulas)) {
            tautology = 1;
            return null;
        }
        List<formula> form = structuralRule1(formulas);
        if(!form.equals(formulas))
            return form;
        form = structuralRule2(formulas);
        if(!form.equals(formulas)) {
            resetIndexes.add(nodeIndex);
            return form;
        }
        form = Rule3(formulas);
        if(!form.equals(formulas))
            return form;
        form = Rule7(formulas);
        if(!form.equals(formulas))
            return form;
        form = Rule6(formulas);
        if(!form.equals(formulas))
            return form;
        for (formula f : abbrev)
            if (abbrevIndex(formulas, f.abbrev) != -1) {
                f.names = formulas.get(abbrevIndex(formulas, f.abbrev)).names;
                formulas.remove(formulas.get(abbrevIndex(formulas, f.abbrev)));
                formulas.add(f);
                return formulas;
            }
        form = Rule5(formulas);
        if(!form.equals(formulas))
            return form;
        return null;
    }


                /* Sort formulas alphabetically and make a string out of every node */
    public static String getNode(List<formula> form) {
        String s = "";
        List<String> list = new ArrayList<>();
        for (formula f : form)
            list.add(f.toString() + f.names);
        Collections.sort(list);
        for(String str:list)
            s = s + str;
        return s;
    }


                /* Print node */
    public static void printNode(List<formula> form) {
        if(form != null)
            for (formula f : form)
                System.out.print(f.toString() + "    " + f.names + "           ,      ");
        System.out.print('\n');
    }


                /* Validate the formula */
    public static boolean isValid(List<formula> formulas, List<formula> abbrev) {
        List<String> nodes = new ArrayList<>();
        List<formula> form = new ArrayList<>();
        nodes.add(getNode(formulas));
        String s = "";
        do {
            nodeIndex ++;
            formulas = applyRules(formulas, abbrev);
            if(formulas != null) {
                printNode(formulas);
                s = getNode(formulas);
                nodes.add(getNode(formulas));
            }
        } while(!nodes.subList(0, nodes.size()-1).contains(s) && tautology == 0);
        return false;
    }


                /* main */
    public static void main(String[] args) throws IOException {
        List<formula> formulas = new ArrayList<>();
        List<formula> abbrev = new ArrayList<>();
        abbrev = abbreviations(new File(args[0]));
        formulas.add(abbrev.get(0));
        List<formula> form = Collections.unmodifiableList(formulas);
        isValid(formulas, abbrev);

    }





}
