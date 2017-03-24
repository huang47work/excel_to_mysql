package com.mason.service;

import com.mason.entity.BankEntity;
import com.mason.entity.PersonBank;
import com.mason.utils.DBHelper;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangsiqian on 2017/3/22 0022.
 */
public class ImportService {
    /**
     * 查询stu表中所有的数据
     * @return
     */
    public static List<BankEntity> getAllByDb(){
        List<BankEntity> list=new ArrayList<BankEntity>();
        try {
            DBHelper db=new DBHelper();
            String sql="select * from stu";
            ResultSet rs= db.Search(sql, null);
            while (rs.next()) {
//                int id=rs.getInt("id");
//                String name=rs.getString("name");
//                String sex=rs.getString("sex");
//                int num=rs.getInt("num");
                String bankName = rs.getString("bank_name");
                String bankCode = rs.getString("bank_code");
                //System.out.println(id+" "+name+" "+sex+ " "+num);
                list.add(new BankEntity(bankName,bankCode));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询指定目录中电子表格中所有的数据
     * @param file 文件完整路径
     * @return
     */
    public static List<PersonBank> getAllByExcel(String file){
        List<PersonBank> list=new ArrayList<PersonBank>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
//            Sheet rs=rwb.getSheet("Test Shee 1");//或者rwb.getSheet(0)
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行

            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String region = rs.getCell(j++, i).getContents();
                    String bankName = rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String supportDesc = rs.getCell(j++, i).getContents();

                    System.out.println("bankName:"+bankName+" supportDesc:"+supportDesc+" region:"+region);
                    list.add(new PersonBank(region,bankName, supportDesc));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }

    /**
     * 通过Id判断是否存在
     * @param name
     * @return
     */
    public static boolean isExist(String name){
        try {
            DBHelper db=new DBHelper();
            ResultSet rs=db.Search("select * from stu where bank_name=?", new String[]{name+""});
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
            //得到表格中所有的数据
            List<PersonBank> listExcel=ImportService.getAllByExcel("e://bank_person.xls");
        /*//得到数据库表中所有的数据
        List<BankEntity> listDb=StuService.getAllByDb();*/

            DBHelper db=new DBHelper();

            for (PersonBank personBank : listExcel) {
//                if (!ImportService.isExist(name)) {
                    //不存在就添加
                    String sql="insert into esign_personal_support_banks (region,bank_name,support_desc) values(?,?,?)";
                    String[] str=new String[]{personBank.getRegion(),personBank.getBankName(),personBank.getSpportDesc()};
                    db.AddU(sql, str);
//                }/*else {
                    //存在就更新
//                    String sql="update stu set bank_name=?,bank_code=?,num=? where id=?";
//                    String[] str=new String[]{bankEntity.getBankName(),bankEntity.getBankCode()};
//                    db.AddU(sql, str);
//                }*/
            }
        }

}
