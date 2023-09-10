
/**
 * Write a description of debugPart1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class debugPart2 {
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1 || index >= input.length() - 3) {
            break;
        }
        String found = input.substring(index+1, index+4);
        System.out.println(index + "," + (index+1) + "," + (index+4));
        System.out.println(found);
        System.out.println("index " + index);
        index = input.indexOf("abc", index+3);
        System.out.println("index after updating " + index);
    }
}
   public void test() {
     // the string is 43 char long.
     //                                     ^  
     findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
     
}

}
