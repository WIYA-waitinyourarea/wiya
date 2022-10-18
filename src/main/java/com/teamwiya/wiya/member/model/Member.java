package com.teamwiya.wiya.member.model;

import com.teamwiya.wiya.TimeStamped;
import com.teamwiya.wiya.member.dto.MemberChangpwdForm;
import com.teamwiya.wiya.member.dto.MemberSaveForm;
import lombok.*;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="memId")
    private Long id; //pk -> memId

    private String memName;
    private String memPwd;
    private String memSalt;
    private String memMail;
    private String memNickname;
    @Enumerated(EnumType.STRING)
    private MemberRole role;


    /**
     *
     * @param memberSaveForm
     * @return
     */
    public static Member createMember(MemberSaveForm memberSaveForm) {
        String salt = createSalt();
        String hashingPwd = hashing(memberSaveForm.getMemPwd(), salt);
        return Member.builder()
                .memName(memberSaveForm.getMemName())
                .memPwd(hashingPwd)
                .memSalt(salt)
                .memMail(memberSaveForm.getMemMail())
                .memNickname(memberSaveForm.getMemNickname())
                //.role(MemberRole.USER)
                .build();
    }



    public void updatePwd(MemberChangpwdForm memberChangpwdForm) {
        String salt = createSalt();
        String hashingPwd = hashing(memberChangpwdForm.getMemPwd(), salt);
        this.memPwd = hashingPwd;
        this.memSalt = salt;
    }


    /**
     * 해싱에 필요한 솔트를 만들어내는 메소드, 회원가입 시에만 사용
     * @return 솔트
     */
    private static String createSalt() {
        return null;
    }

    /**
     * 해싱하는 메소드, 회원가입 과 로그인 시 사용
     * @param memPwd
     * @param salt
     * @return
     */
    public static String hashing(String memPwd, String salt) {
        //평문 -> 바이트 //몽실123
        byte[] bytesPwd = memPwd.getBytes();
        //SHA-256 해쉬 알고리즘
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            //스트레칭 10번 + 매번 솔팅
            for (int i = 0; i < 10; i++) {
                //바이트 -> 16진수 -> 스트링 + 솔팅
                String temp = bytesToString(bytesPwd) + salt;
                //해싱
                messageDigest.update(temp.getBytes());
                bytesPwd = messageDigest.digest();
            }
            //다이제스팅된 바이트 -> 문자열
            return bytesToString(bytesPwd);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("잘못된 해시 알고리즘");
        }
    }

    private static String bytesToString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b: bytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}