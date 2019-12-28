import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**
 * Calender
 */
public class Calender {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter timings for user1: ");
        String yash_time=sc.nextLine();
        System.out.println("Enter timings for user2: ");
        String piyush_time=sc.nextLine();
        String[] part_yash=yash_time.split(",");
        String[] part_piyush=piyush_time.split(",");
        ArrayList<String> free_time_1=new ArrayList<>();
        ArrayList<String> free_time_2=new ArrayList<>();
        String free_timestr="";
        free_timestr = getFreeTime(part_piyush, free_time_2, free_timestr);
        free_timestr = getFreeTime(part_yash, free_time_1, free_timestr);
        ArrayList<String> free_time=new ArrayList<>(free_time_1);
        ArrayList<String> common_free_time=new ArrayList<>();
        free_time.addAll(free_time_2);
        free_timestr="";
        Collections.sort(free_time);
        System.out.println(free_time);
        for (int i = 1; i < free_time.size(); i++) {
            if(free_time_1.contains(free_time.get(i))&&free_time_1.contains(free_time.get(i-1))){
                i++;
            }
            else
            {
                String[] time_slots1=free_time.get(i).split("-");
                String[] time_slots2=free_time.get(i-1).split("-");
                float maxval=Math.max(Float.parseFloat(time_slots1[0]),Float.parseFloat(time_slots2[0]));
                float minval=Math.min(Float.parseFloat(time_slots1[1]),Float.parseFloat(time_slots2[1]));
                common_free_time.add(maxval+"-"+minval);
                i++;
            }
        }
        System.out.println(common_free_time);
        sc.close();
    }

    private static String getFreeTime(String[] part_piyush, ArrayList<String> free_time_2, String free_timestr) {
        for (int i = 1; i < part_piyush.length; i++) {
            for(int k=1;k<6;k++)
            {
                if(k==3)
                {
                    free_timestr+='.';
                    k++;
                }
            free_timestr+=part_piyush[i-1].charAt(k+6);  
            }
            free_timestr+="-";
            for(int k=1;k<6;k++)
            {
                if(k==3)
                {
                    free_timestr+='.';
                    k++;
                }
            free_timestr+=part_piyush[i].charAt(k);  
            }

            if(!(part_piyush[i-1].charAt(7)==part_piyush[i].charAt(1)&&part_piyush[i-1].charAt(8)==part_piyush[i].charAt(2)&& part_piyush[i-1].charAt(10)==part_piyush[i].charAt(4)&&part_piyush[i-1].charAt(11)==part_piyush[i].charAt(5)))
            free_time_2.add(free_timestr);
            free_timestr="";
        }
        return free_timestr;
    }
}