/**
 * Created by s1309096 on 13/02/17.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
// TODO: Priority : thin>reset> 3,4 > 6,7+unfolding >5 DONE
// TODO: FIX AND rule   -  urgent
// TODO: Have individual nodes for each tree   - urgent
// TODO: Check only between the identical nodes for reset/common name
// TODO: Refractor everything
// TODO: ADD more variables (maybe X1-X9)
// TODO: review rule 6 and rule 7
public class muCalculus {
    public static List<formula> abbrev = new ArrayList<formula>();
    public static List<String> nodes = new ArrayList<>();
    public static List<String> names = new ArrayList<>();
    public static int resetFlag = 0;
    public static int tautology = 0;
    public static int validity = 1;

        public static char findFirstVariable(String str) {
            String variables = "TWXYZ";
            for(int i =0; i< str.length(); i++) {
                if(variables.indexOf(str.charAt(i)) != -1)
                return str.charAt(i);
            }
            return 'A';
        }

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
        // TODO : does this mean it is valid/ ( why not the same as rule 2)? YES-it is the same
        /*  Rule 1 - when reaching a tautology  */
        public static boolean Rule1(List<formula> form) {
            int p =0, np =0;
            for(formula f : form) {
                if(f.toString().compareTo("P") == 0)
                    p = 1;
                if(f.toString().compareTo("NP") == 0)
                    np = 1;
            }
            if(p ==1 && np ==1) {
                names.add(names.get(names.size() - 1));
                return false;
            }
            if(names.size()!=0)
                names.add(names.get(names.size() - 1));
            return true;
        }
        //TODO : is this (F or notF)
        /*  Rule 2 - same aas Rul1 1    */
        public static boolean Rule2(List<formula> form) {
            for(formula f : form)
                if(f.toString().startsWith("N")) {
                    for(formula g: form)
                        if(f.toString().substring(1, f.toString().length()).equals(g.toString()))
                            return false;
                }
            if(names.size() != 0)
                names.add(names.get(names.size() - 1));
            return true;
        }

        /* OR Rule  */ // TODO: find the right comma where to split  -> DONE
        public static boolean Rule3(List<formula> form) {
            for(formula f :form)
                if(f.toString().startsWith("or")) {
                    int paratheses = 0;
                    int commaIndex =0;
                    for(commaIndex =0; commaIndex<f.toString().length(); commaIndex++){
                        if(f.toString().charAt(commaIndex) == '(')
                            paratheses++;
                        else if(f.toString().charAt(commaIndex) == ')')
                            paratheses--;
                        else if(f.toString().charAt(commaIndex) == ',' && paratheses ==1)
                            break;
                    }

                    formula f1 = new formula(f.toString().substring(3, commaIndex-1));
                    formula f2 = new formula(f.toString().substring(commaIndex + 2, f.toString().length() - 1));
                    f1.names = f.names;
                    f1.nameNumber = f.nameNumber;
                    f2.names = f.names;
                    f2.nameNumber = f.nameNumber;
                    form.remove(f);
                    form.add(f1);
                    form.add(f2);
                    names.add(names.get(names.size() - 1));
                    return false;
                }
            if(names.size() != 0)
                names.add(names.get(names.size() - 1));
            return true;
        }

        //TODO : AND Rule   , see rule above
        // TODO: shitty trees are changing states --> maybe do a vector of lists--looks like lists of list may be the solution
        public static boolean Rule4(List<formula> form) {
            for(formula f:form)
                if(f.toString().startsWith("and")) {
                    int paratheses = 0;
                    int commaIndex =0;
                    for(commaIndex =0; commaIndex<f.toString().length(); commaIndex++){
                        if(f.toString().charAt(commaIndex) == '(')
                            paratheses++;
                        else if(f.toString().charAt(commaIndex) == ')')
                            paratheses--;
                        else if(f.toString().charAt(commaIndex) == ',' && paratheses ==1)
                            break;
                    }

                    formula f1 = new formula(f.toString().substring(4, commaIndex-1));
                    f1.names = f.names;
                    f1.nameNumber = f.nameNumber;
                    formula f2 = new formula(f.toString().substring(commaIndex + 2, f.toString().length() -1));
                    f2.names = f.names;
                    f2.nameNumber =f.nameNumber;
                    form.remove(f);
                    List<formula> tree1 = new ArrayList<>();
                    tree1.addAll(form);
                    List<formula> tree2 = new ArrayList<>();
                    tree2.addAll(form);
                    tree1.add(f1);
                    tree2.add(f2);
                    System.out.print("\nTREE branch1 : \n");
                    boolean b1 =isValid(tree1, abbrev);

                    System.out.print("\nTREE branch2 : \n");
                    boolean b2 = isValid(tree2, abbrev);
                    return false;
                }

            return true;
        }

        // TODO: can only be done if Gamma is a boolean or modal formulas (make it as least priority) -->DONE
        // TODO: ALSO remove all other formulas --> DONE
        /*   Modal Rule     */
        public static boolean Rule5(List<formula> form) {
            List<formula> toRemove =new ArrayList<>();
            List<formula> toAdd = new ArrayList<>();
            formula f1 = new formula("");
            for(formula f: form)
                if(f.toString().startsWith("[a]")) {
                    for (formula g : form)
                        if (g.toString().length() > 3)
                            if (g.toString().startsWith("<a>")) {
                                formula g1 = g;
                                g1.form = g.toString().substring(3, g.toString().length());
                                toAdd.add(g1);
                            }
                    f1 = f;
                    f1.form = f.toString().substring(3, f.toString().length());
                    break;
                }
            form.clear();
            form.add(f1);
            form.addAll(toAdd);
            names.add(names.get(names.size() - 1));
            return true;
        }

        /*   Unfolding least fixed point     */
        public static boolean Rule6(List<formula> form) {
            for(formula f: form)
                if(f.toString().startsWith("m")) {
                    formula f1 = new formula(f.toString().substring(3, f.toString().length()));
                    f1.names = f.names;
                    f1.nameNumber = f.nameNumber;
                    form.remove(f);
                    form.add(f1);
                    if(names.size() != 0)
                        names.add(names.get(names.size() - 1));
                    return false;
                }
            if(names.size() != 0)
                names.add(names.get(names.size() - 1));
            return true;
        }

        /*      Unfolding most fixed point      */
        public static boolean Rule7(List<formula> form) {
            for(formula f:form)
                if(f.toString().startsWith("n")) {
                    formula f1 = new formula(f.toString().substring(3, f.toString().length()));
                    if(f.names !=null)
                        f1.nameNumber= f.names.length()/2 +1;
                    else
                        f1.nameNumber = 1;
                    if(f.names != null) {
                        f1.names = f.names + f.toString().toLowerCase().charAt(1) + f1.nameNumber;
                        if(names.size() != 0)
                            names.add(names.get(names.size() - 1) + f.toString().toLowerCase().charAt(1) + f1.nameNumber);
                        else
                            names.add(""+f.toString().toLowerCase().charAt(1) + f1.nameNumber);
                    }
                    else {
                        f1.names = Character.toString(f.toString().toLowerCase().charAt(1)) + f1.nameNumber;
                        if(names.size() != 0)
                            names.add(names.get(names.size() -1) + f.toString().toLowerCase().charAt(1) + f1.nameNumber);
                        else
                            names.add("" + f.toString().toLowerCase().charAt(1) + f1.nameNumber);
                    }
                    form.remove(f);
                    form.add(f1);

                    return false;
                }
            return true;
        }

        /*  Thin Rule   */
        public static boolean structuralRule1(List<formula> form) {
            int x = 0;
            for(formula f: form)
                for(formula g: form)
                    if(f.toString().equals(g.toString()) && f != g) {//!(f.names == null ? g.names == null: f.names.equals(g.names) )) {
                        if(f.names.startsWith(g.names)) {
                            names.add(names.get(names.size()-1));
                            form.remove(g);
                            return false;
                        }
                        if(g.names.startsWith(f.names)) {
                            names.add(names.get(names.size()-1));
                            form.remove(f);
                            return false;
                        }
                    }
            return true;
        }


        /* rewrite reset */
                            /* check if there is 2 strings have the same prefix */
        public static String resetStrings(String str1, String str2, List<formula> form) {
            int i = 0;
            int flag  = 1;
            if(str1 == null || str2 == null)
                return null;
            while(flag == 1 && i < str1.length()-1 && i < str2.length()) {
                i = i + 2;
                if(str1.startsWith(str2.substring(0,i)))
                    for(formula f : form) {
                        if(f.names != null)
                        if(f.names.length()>= i && f.names.startsWith(str2.substring(0,i)) && f.names.length() == i)
                            flag = 2;
                    }
                else
                    flag = 0;
                if(flag == 1)
                    break;
                if(flag == 2)
                    flag = 1;

            }
            if(i > 0 && i <  str1.length()-1 && i<str2.length() -1)
                return str1.substring(0, i);
            return null;
        }

        /* perform the reset on all formulas having the same prefix name */
        public static boolean doReset(List<formula> form, String prefix) {
            for(formula f: form) {
                if(f.names != null)
                    if(f.names.startsWith(prefix) && f.names.length() > prefix.length())
                        f.names = prefix;
            }
            return true;
        }

        public static boolean structuralRule2(List<formula> form) {
            if(form.size() == 1)
                if(form.get(0).names != null && form.get(0).names.length()>2) {
                    form.get(0).names = form.get(0).names.substring(0, 2);
                    return false;
                }
            for(formula f: form)
                for(formula g : form)
                 {
                        String prefix = resetStrings(f.names, g.names, form);
                        if(prefix != null) {
                            doReset(form, prefix);
                            return false;
                        }
            }
            return true;
        }

        /* Resources    */
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
        public static String sortFormulas(List<formula> form) {
            String s = "";
            List<String> list = new ArrayList<>();
            for (formula f : form)
                list.add(f.toString() + f.names);
            Collections.sort(list);
            for(String str:list)
                s = s + str;
            return s;
        }
        public static void addToNode(List<formula> form) {
            nodes.add(sortFormulas(form));
        }
        public static boolean applyRules(List<formula> abbrev, List<formula> formulas) {
            String s = "";
            if(!Rule1(formulas) || !Rule2(formulas)){
                addToNode(formulas);
                tautology = 1;
                return true;
            }
            else
                if(!structuralRule1(formulas)) {
                    addToNode(formulas);
                    return true;
                }
                else
                    if (!structuralRule2(formulas)) {
                        System.out.print("RESET\n");
                        resetFlag = 1;
                        addToNode(formulas);
                        return true;
                    }
                    else
                        if(!Rule3(formulas)) {
                            addToNode(formulas);
                            return true;
                        }
                        else
                            if(!Rule4(formulas)) {
                                addToNode(formulas);
                                return true;
                            }
                            else
                                if(!Rule7(formulas)) {
                                    addToNode(formulas);
                                    return true;
                                }
                                else
                                    if(!Rule6(formulas)) {
                                        addToNode(formulas);
                                        return true;
                                    }
                                    else
                                        for (formula f : abbrev)
                                            if (abbrevIndex(formulas, f.abbrev) != -1) {
                                                f.names = formulas.get(abbrevIndex(formulas, f.abbrev)).names;
                                                formulas.remove(formulas.get(abbrevIndex(formulas, f.abbrev)));
                                                formulas.add(f);
                                                addToNode(formulas);
                                                return false;
                                            }
            Rule5(formulas);
            addToNode(formulas);
            return true;

        }
        // TODO: check for names only in between the two identical nodes
        public static boolean isValid(List<formula> formulas, List<formula> abbrev) {
            String s = "";
            for (formula f1 : formulas) {
                System.out.print(f1.toString() + "    " + f1.names + "           ,      ");
                s = s + f1.toString() + f1.names;
            }
            System.out.print("\n");
            do {
                s = "";
                applyRules(abbrev, formulas);
                if(tautology ==1) {
                    //System.out.print("Reached tautology");
                    return true;
                }
                for (formula f : formulas) {
                    System.out.print(f.toString() + "    " + f.names + "           ,      ");
                }
                s = sortFormulas(formulas);
                System.out.print('\n');
            } while(!nodes.subList(0, nodes.size()-1).contains(s));
            //System.out.print(names);

            // Check if there is name that appears in all w
            int valid = 1;
            for(String str: names.subList(0, names.size()-2)) {
                for(int i =0;i< (names.get(names.size()-1)).length(); i+=2) {
                    if (!str.contains(names.get(names.size() - 1).substring(i, i = 1)))
                        valid = 0;
                    if (valid == 1)
                        break;
                }
                if(valid ==1)
                    break;
            }
            if(valid == 1 && resetFlag ==1) {
                resetFlag = 0;
                tautology = 0;
                //System.out.print("\nFormula is valid");
                return true;
            }
            else {  validity = 0;
                    //System.out.print("\nFormula is not valid");
                    return false;
                 }

        }

        public static void main(String[] args) throws IOException {
            List<formula> formulas= new ArrayList<>();
//            abbrev = abbreviations(new File(args[0]));
//            formulas.add(abbrev.get(0));
//            nodes.add(formulas.get(0).toString());

            // TODO : go over this example. Is it correct? Do P and not P have to have the same names to be a tautology? YES
            formula f1  = new formula("nZ.or([a]Z , W)");
            f1.abbrev = "Z";
            formulas.add(f1);
            formula f2 = new formula("mW.and([a]W , NP)");
            f2.abbrev = "W";

            formula f3 = new formula("nX.and(<a>X , Y)");
            f3.abbrev = "X";
            formula f4 = new formula("mY.or(<a>Y , P)");
            f4.abbrev = "Y";
            formulas.add(f3);
            abbrev.add(f3); abbrev.add(f4); abbrev.add(f1); abbrev.add(f2);

            isValid(formulas, abbrev);
            if(validity == 1)
                System.out.print("Formula is valid");
            else
                System.out.print("Formula is not valid");
       }
}
