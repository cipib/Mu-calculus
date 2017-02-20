/**
 * Created by s1309096 on 13/02/17.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// TODO: Priority : thin>reset> 3,4 > 6,7+unfolding >5
public class muCalculus {
    public static List<formula> formulas= new ArrayList<formula>();
    public static List<String> nodes = new ArrayList<>();
    public static List<String> names = new ArrayList<>();
    public static int resetFlag =0;
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
            String content = new Scanner(input).useDelimiter("\\Z").next();
            String form;
            int k =0;
            for(int j =0; j<content.length(); j++) {
                if(content.charAt(j) == '.') {
                    if(content.substring(j+1).indexOf('.') != -1) {
                        formula f = new formula(content.substring(k, j+1) + findFirstVariable(content.substring(j, content.length()-1)));
                        f.abbrev = Character.toString(findFirstVariable(f.toString()));
                        abbrev.add(f);
                        k = j;
                    }
                    else {
                        formula f = new formula(content.substring(k+1, content.length()));
                        f.abbrev = Character.toString(findFirstVariable(f.toString()));
                        abbrev.add(f);
                    }
                }
            }

            return abbrev;
        }
        // TODO : does this mean it is valid/ ( why not the same as rule 2)?
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
            names.add(names.get(names.size() - 1));
            return true;
        }
        //TODO : is this (F or notF)
        public static boolean Rule2(List<formula> form) {
            return true;
        }

        public static boolean Rule3(List<formula> form) {
            for(formula f :form)
                if(f.toString().startsWith("or")) {
                    formula f1 = new formula(f.toString().substring(3, f.toString().indexOf(',')-1));
                    formula f2 = new formula(f.toString().substring(f.toString().indexOf(',') + 2, f.toString().length() - 1));
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
            names.add(names.get(names.size() - 1));
            return true;
        }

        //TODO
        public static boolean Rule4(List<formula> form) {
            return true;
        }
        // TODO: can only be done if Gamma is a boolean or modal formulas
        public static boolean Rule5(List<formula> form) {
            for(formula f: form)
                if(f.toString().startsWith("[a]")) {
                    for (formula g : form) {
                        if (g.toString().length() > 3) {
                            if (g.toString().startsWith("<a>") &&(g.names ==null ? f.names ==null: g.names.equals(f.names)) ) {

                                formula g1 = new formula(g.toString().substring(3, g.toString().length()));
                                g1.names = g.names;
                                g1.nameNumber = g.nameNumber;
                                form.remove(g);
                                form.add(g1);
                            }
                        }
                    }
                        formula f1 = new formula(f.toString().substring(3, f.toString().length()));
                        f1.nameNumber = f.nameNumber;
                        f1.names = f.names;
                        form.remove(f);
                        form.add(f1);
                        names.add(names.get(names.size() - 1));
                        return false;
                }
            names.add(names.get(names.size() - 1));
            return true;
        }

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

        public static int checkReset(String str1, String str2) {
            if(str1 == null) {
                if (str2 != null && str2.length() > 2)
                    return 1;
            }
            else if(str2!=null)
                for(int i=0; i<str2.length(); i+=2)
                    if(!str1.contains(str2.substring(i, i+1)) && i!=str2.length()-2)
                        return i+1;
            return -1;
        }
        public static String removeName(String str, String names) {
            String str2 = str;
            for(int i = 0; i<names.length(); i+=2) {
                str2 = str.replace("z2", "");
                str = str2;
            }
            return str2;
        }
    // TODO: each formula must have both common and uncommon names
        public static boolean structuralRule2(List<formula> form) {
            for(formula f : form)
                for(formula g :form) {
                    if (checkReset(f.names, g.names) != -1) {
                        int l = g.names.length();
                        names.add(removeName(names.get(names.size()-1), g.names.substring(checkReset(f.names, g.names)+1, l)));
                        g.names = g.names.substring(0, checkReset(f.names, g.names)+1);

                        return false;
                    }
                    if (checkReset(g.names, f.names) != -1) {
                        int l = f.names.length();
                        names.add(removeName(names.get(names.size()-1), f.names.substring(checkReset(g.names, f.names)+1, l)));
                        f.names = f.names.substring(0, checkReset(g.names, f.names)+1);
                        return false;
                    }

                }
            return true;
        }

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

        public static boolean applyRules(List<formula> abbrev) {
            String s = "";
//            if(structuralRule2(formulas))
//                if(structuralRule1(formulas))
//                    if(Rule7(formulas))
//                        if(Rule6(formulas))
//                            if(Rule5(formulas))
//                                if(Rule3(formulas))
//                                    if(Rule1(formulas))
            if(structuralRule1(formulas)) {
                if (!structuralRule2(formulas)) {
                    System.out.print("RESET\n");
                    resetFlag = 1;
                    for (formula f : formulas)
                        s = s + f.toString() + f.names;
                    nodes.add(s);
                    return true;
                }
            }
            else {
                for (formula f : formulas)
                    s = s + f.toString() + f.names;
                nodes.add(s);
                return true;
            }
            if(structuralRule1(formulas) && structuralRule2(formulas) && Rule7(formulas) && Rule6(formulas)
                    && Rule5(formulas) && Rule3(formulas) && Rule1(formulas)) {
                for (formula f : abbrev)
                    if (abbrevIndex(formulas, f.abbrev) != -1) {
                        f.names = formulas.get(abbrevIndex(formulas, f.abbrev)).names;
                        formulas.remove(formulas.get(abbrevIndex(formulas, f.abbrev)));
                        formulas.add(f);
                        return false;
                    }
            }
            else
                for(formula f: formulas)
                    s = s + f.toString() +f.names;
            nodes.add(s);
            return true;
        }
        public static void main(String[] args) throws IOException {
            List<formula> abbrev = new ArrayList<formula>();
            abbrev = abbreviations(new File(args[0]));
            formulas.add(abbrev.get(0));
            nodes.add(formulas.get(0).toString());
            formula e = new formula("E");
            formulas.add(e);
            // TODO : go over this example. Is it correct? NO
//            formula f1  = new formula("nZ.or([a]Z , W)");
//            f1.abbrev = "Z";
//            abbrev.add(f1);
//            formulas.add(f1);
//            formula f2 = new formula("mW.or([a]W or NP)");
//            f2.abbrev = "W";
//            abbrev.add(f2);
            /*
            formula f3 = new formula("nX.or(<a>X , Y)");
            f3.abbrev = "X";
            formula f4 = new formula("mY.or(<a>Y , P");
            f4.abbrev = "Y";
            formulas.add(f3);
            abbrev.add(f3); abbrev.add(f4);
            */
            // TODO : turn all these into methods
            String s = "";
            for (formula f1 : formulas) {
                System.out.print(f1.toString() + "    " + f1.names + "           ,      ");
                s = s + f1.toString() + f1.names;
            }
            System.out.print("\n");
            do {
                s = "";
                applyRules(abbrev);
                for (formula f : formulas) {
                    System.out.print(f.toString() + "    " + f.names + "           ,      ");
                    s = s + f.toString() + f.names;
                }
                System.out.print('\n');
            } while(!nodes.subList(0, nodes.size()-1).contains(s));
            System.out.print(names );

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
            if(valid == 1 && resetFlag ==1)
                System.out.print("\nFormula is valid");
            else
                System.out.print("\nFormula is not valid");


       }
}
