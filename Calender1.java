import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Calender
 */
public class Calender1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter working hours for user1: ");
        String yash_hours=sc.nextLine();
        System.out.println("Enter timings for user1: ");
        String[] part_yash = sc.nextLine().split(",");
        System.out.println("Enter working hours for user2: ");
        String piyush_hours=sc.nextLine();
        System.out.println("Enter timings for user2: ");
        String[] part_piyush = sc.nextLine().split(",");
        ArrayList<String> free_time_1 = new ArrayList<>();
        ArrayList<String> free_time_2 = new ArrayList<>();
        free_time_1 = firstSlot(yash_hours, part_yash, free_time_1);
        free_time_1.addAll(lastSlot(yash_hours, part_yash, free_time_1));
        free_time_2=firstSlot(piyush_hours, part_piyush, free_time_2);
        free_time_2.addAll(lastSlot(piyush_hours, part_piyush, free_time_2));
        System.out.println("\n\n"+free_time_1+"\n");
        System.out.println(free_time_2+"\n\n");
        String free_timestr = "";
        free_timestr = getFreeTime(part_piyush, free_time_2, free_timestr);
        free_timestr = getFreeTime(part_yash, free_time_1, free_timestr);
        ArrayList<String> free_time = new ArrayList<>(free_time_1);
        ArrayList<String> common_free_time = new ArrayList<>();
        free_time.addAll(free_time_2);
        free_timestr = "";
        Collections.sort(free_time);
        for (int i = 1; i < free_time.size(); i++) {
            if (free_time_1.contains(free_time.get(i)) && free_time_1.contains(free_time.get(i - 1))) {
                i++;
            } else {
                String[] time_slots1 = free_time.get(i).split("[-]");
                String[] time_slots2 = free_time.get(i - 1).split("[-]");
                if((time_slots1[0]).compareTo(time_slots2[1])<0)
                {
                    
                    common_free_time.add(maxFunction(time_slots1[0], time_slots2[0]) + "-"
                            + minFunction(time_slots1[1], time_slots2[1]));
                    
                }
                i++;
            }
        }
        System.out.println(common_free_time);
        sc.close();
    }

    private static ArrayList<String> firstSlot(String yash_hours, String[] part_yash, ArrayList<String> free_time_11) {
        String temp1="";
        String temp2="";
        
        for (int i = 1; i < 6; i++) {
            temp1+=yash_hours.charAt(i);
            temp2+=part_yash[0].charAt(i);
        }
        if(temp1.compareTo(temp2)<0)
        {
            free_time_11.add(temp1+"-"+temp2);
        }
        System.out.println("In first slot:"+free_time_11);
        return free_time_11;
    }
    private static ArrayList<String> lastSlot(String yash_hours, String[] part_yash, ArrayList<String> free_time_12) {
        String temp1="";
        String temp2="";
        for (int i = 7; i < 12; i++) {
            temp1+=yash_hours.charAt(i);
            temp2+=part_yash[part_yash.length-1].charAt(i);
        }
        if(temp1.compareTo(temp2)>0)
        {
            free_time_12.add(temp2+"-"+temp1);
        }
        System.out.println("In last slot:"+free_time_12);
        return free_time_12;
    }

    private static String getFreeTime(String[] part_piyush, ArrayList<String> free_time_2, String free_timestr) {
        for (int i = 1; i < part_piyush.length; i++) {
            for (int k = 1; k < 6; k++)

                free_timestr += part_piyush[i - 1].charAt(k + 6);

            free_timestr += "-";
            for (int k = 1; k < 6; k++)
                free_timestr += part_piyush[i].charAt(k);

            if (!(part_piyush[i - 1].charAt(7) == part_piyush[i].charAt(1)
                    && part_piyush[i - 1].charAt(8) == part_piyush[i].charAt(2)
                    && part_piyush[i - 1].charAt(10) == part_piyush[i].charAt(4)
                    && part_piyush[i - 1].charAt(11) == part_piyush[i].charAt(5)))
                free_time_2.add(free_timestr);
            free_timestr = "";
        }
        return free_timestr;
    }

    static String maxFunction(String s1, String s2) {
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < s1.length(); i++) {
            sum1 += s1.charAt(i);
            sum2 += s2.charAt(i);
        }
        return ((sum1 > sum2) ? s1 : s2);
    }

    static String minFunction(String s1, String s2) {
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < s1.length(); i++) {
            sum1 += s1.charAt(i);
            sum2 += s2.charAt(i);
        }
        return ((sum1 < sum2) ? s1 : s2);
    }
}