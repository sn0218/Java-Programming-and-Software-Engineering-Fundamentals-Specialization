
/**
 * Write a description of debugPart1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class debugPart1 {
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1 || index >= input.length() - 3) {
            break;
        }
        String found = input.substring(index+1, index+4);
        System.out.println(index + "," + (index+1) + "," + (index+4));
        System.out.println(found);
        index = input.indexOf("abc", index+4);
    }
}
   public void test() {
     findAbc("abcdabc");
     
}

}
