package HW4.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description Contact Item
 * @Author: liuXuyang
 * @studentNo 15130110024
 * @Emailaddress 1187697635@qq.com
 * @Date: 2018/3/25 15:37
 */
public class PIMContact extends PIMEntity {

    private Set<PersonDetail> set = new HashSet<>();

    public Set<PersonDetail> getSet() {
        return set;
    }

    public void setSet(Set<PersonDetail> set) {
        this.set = set;
    }

    @Override
    public void fromString(String s) {
        String[] strings = s.split(";");
        for(int i=0;i<strings.length-1;i++)
        {
            PersonDetail personDetail = new PersonDetail();
            String[] message = strings[i].split(" ");
            personDetail.setFirstName(message[0]);
            personDetail.setLastName(message[1]);
            personDetail.setEmailAddress(message[2]);
            set.add(personDetail);
        }
        this.setPriority(strings[strings.length-1]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Contact "+getPriority());
        set.forEach(personDetail -> sb.append(personDetail.toString()));
        sb.append(" "+owner);
        sb.append(" "+status);
        return sb.toString();
    }
}
