package com.lsnu.sms.utils;

import com.lsnu.sms.dao.*;
import com.lsnu.sms.model.Attendance;
import com.lsnu.sms.model.AttendanceSet;
import com.lsnu.sms.model.Insurance;
import com.lsnu.sms.model.Wages;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Calculation  implements ApplicationContextAware {

    private static  ApplicationContext applicationContext;


    @Autowired
    private StaffDao staffDao;
    @Autowired
    private SalaryDao salaryDao;
    @Autowired
    private AttendanceSetDao attendanceSetDao;
    @Autowired
    private AttendanceDao attendanceDao;
    @Autowired
    private InsuranceDao insuranceDao;

    private static Calculation calculation;
    @PostConstruct
    public void init(){
        calculation = this;
    }

    public  Calculation(){}
    @Autowired
    public void setStaffDao(StaffDao staffDao){
        this.staffDao=staffDao;
    }

    public Wages calculation(String workId,String times){
        Wages wages = new Wages();
        wages.setWorkId(workId);
        wages.setTimes(times);
        StaffDao staffDao = (StaffDao) applicationContext.getBean("staffDao");
        String trueName = staffDao.getInfo(workId).getTrueName();

        SalaryDao salaryDao = (SalaryDao) applicationContext.getBean("salaryDao");
        String baseSalary = salaryDao.getValue(workId);

        AttendanceSetDao attendanceSetDao = (AttendanceSetDao) applicationContext.getBean("attendanceSetDao");
        AttendanceSet attendanceSet = attendanceSetDao.getList();

        AttendanceDao attendanceDao = (AttendanceDao) applicationContext.getBean("attendanceDao");
        Attendance attendance = attendanceDao.getValues(wages);

        InsuranceDao insuranceDao = (InsuranceDao) applicationContext.getBean("insuranceDao");
        Insurance insurance = insuranceDao.getList();

        wages.setTrueName(trueName);
        wages.setBaseSalary(baseSalary);
        wages.setLate(attenCalc(attendanceSet.getLate(),attendance.getLateTimes()));
        wages.setEarly(attenCalc(attendanceSet.getEarlyLeave(),attendance.getEarlyTimes()));
        wages.setLeave(attenCalc(attendanceSet.getLeave(),attendance.getLeaveTimes()));
        wages.setOvertimes(attenCalc(attendanceSet.getOvertime(),attendance.getOvertimeHours()));
        wages.setEndowment(insCalc(baseSalary,insurance.getEndowment()));
        wages.setUnemployment(insCalc(baseSalary,insurance.getUnemployment()));
        wages.setInjury(insCalc(baseSalary,insurance.getEmploymentInjury()));
        wages.setMaternity(insCalc(baseSalary,insurance.getMaternity()));
        wages.setMedical(insCalc(baseSalary,insurance.getMedical()));
        wages.setAccumulation(insCalc(baseSalary,insurance.getAccumulation()));
        wages.setFinalSalary(finalCalc(wages));
        return wages;
    }
    //计算实发工资
    private String finalCalc(Wages wages) {
        double res = Double.parseDouble(wages.getBaseSalary())-Double.parseDouble(wages.getLate())
                -Double.parseDouble(wages.getEarly())-Double.parseDouble(wages.getLeave())
                +Double.parseDouble(wages.getOvertimes())-Double.parseDouble(wages.getEndowment())
                -Double.parseDouble(wages.getUnemployment())-Double.parseDouble(wages.getInjury())
                -Double.parseDouble(wages.getMaternity())-Double.parseDouble(wages.getMedical())
                -Double.parseDouble(wages.getAccumulation());
        return String.valueOf(res);
    }

    //计算保险
    private String insCalc(String baseSalary, int num) {
        double res = Double.parseDouble(baseSalary)*0.01*num;
        return String.valueOf(res);
    }
    //计算考勤
    private String attenCalc( String num1, int num2) {
        int res = Integer.parseInt(num1)*num2;
        return String.valueOf(res);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(Calculation.applicationContext==null){
            this.applicationContext=applicationContext;
        }
    }
}
