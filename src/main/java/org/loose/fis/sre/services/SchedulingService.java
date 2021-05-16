package org.loose.fis.sre.services;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.Scheduling;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToScheduling;

public class SchedulingService {

    private static ObjectRepository<Scheduling> schedulingRepository;
    //private static ObjectRepository<House> houseRepository;

    public static void initDatabase() {
        FileSystemService.initDirectoryScheduling();
        Nitrite database = Nitrite.builder()
                .filePath(getPathToScheduling("scheduling-database.db").toFile())
                .openOrCreate("agent_imb", "agent_imb");

        schedulingRepository = database.getRepository(Scheduling.class);
    }

    public static List<Scheduling> getAllScheduling() {
        return schedulingRepository.find().toList();
    }
    public static ObjectRepository<Scheduling> getSchedulingRepository()
    {
        return schedulingRepository;
    }
    public static void addScheduling(String address, String day, String month, String year, String hour, String agentSchedule, String special_req, String user) throws SchedulingAlreadyExistsException, IncorrectNameException, HouseDoesNotExistsException, IncorrectDateException, AgentDoesNotExistException
    {
        checkchedulingDoesNotAlreadyExist(day,month,year,hour,agentSchedule);
        UserService.checkAgentDoesExist(agentSchedule);
        UserService.checkUsername(user);
        HouseService.checkAddressDoesExist(address);
        checkDate(day,month,year);
        schedulingRepository.insert(new Scheduling(address,day,month,year,hour,agentSchedule,special_req,user));
    }

     static void checkchedulingDoesNotAlreadyExist(String day, String month, String year, String hour, String agent_book) throws SchedulingAlreadyExistsException {
        for (Scheduling scheduling : schedulingRepository.find()) {
            if (Objects.equals(day, scheduling.getDay()) && Objects.equals(month, scheduling.getMonth()) && Objects.equals(year, scheduling.getYear()) && Objects.equals(hour, scheduling.getHour())  && Objects.equals(agent_book, scheduling.getAgentSchedule()))
                throw new SchedulingAlreadyExistsException(day,month,year,hour,agent_book);
        }
    }
    private static void checkDate(String day,String month,String year) throws IncorrectDateException {
        if((Objects.equals(day,"30") || Objects.equals(day,"31")) &&(Objects.equals(month,"February")))
        {
            throw new IncorrectDateException(day,month,year);
        }
        if((Objects.equals(day,"31")) && (Objects.equals(month,"April") || Objects.equals(month,"June") || Objects.equals(month,"September") ||  Objects.equals(month,"November")))
        {
            throw new IncorrectDateException(day,month,year);
        }
    }

    public static String seeSchedulings(String Name) throws NoSchedulingsException
    {
        UserService.CheckNameCredentials(Name);
        String s="";
        for (Scheduling scheduling : schedulingRepository.find())
        {
            if(Objects.equals(Name, scheduling.getAgentSchedule())) {
                s = s + scheduling.toString();
                s = s + "\n";
            }
        }
        return s;
    }
    public static String  seeHistorySchedulings(String Name) throws NoSchedulingsException
    {
        UserService.CheckNameCredentials(Name);
        String s="";
        for (Scheduling scheduling : schedulingRepository.find())
        {
            if(Objects.equals(Name, scheduling.getUser())) {
                s += scheduling.toString();
                s += "\n";
            }
        }
        return s;
    }
    public static void approveScheduling(String Name, String hour,  String day,String month, String year) throws IncorrectDateException, AgentDoesNotExistException, SchedulingNotFoundException
    {
        UserService.checkAgentDoesExist(Name);
        checkDate(day,month,year);
        for (Scheduling scheduling : schedulingRepository.find())
        {
            if(Objects.equals(Name, scheduling.getAgentSchedule()) && Objects.equals(day, scheduling.getDay()) && Objects.equals(month, scheduling.getMonth()) && Objects.equals(year, scheduling.getYear()) && Objects.equals(hour, scheduling.getHour() ))

            {
                scheduling.setAcceptScheduling("approved");
                scheduling.setRejection_message("");
                schedulingRepository.update(scheduling);
                return;
            }
        }
        throw new SchedulingNotFoundException(Name, hour, day, month, year);
    }
    public static void rejectScheduling(String Name, String hour, String day, String month, String year, String Reason) throws IncorrectDateException, AgentDoesNotExistException, SchedulingNotFoundException
    {
        UserService.checkAgentDoesExist(Name);
        checkDate(day,month,year);
        for (Scheduling scheduling : schedulingRepository.find())
        {
            if(Objects.equals(Name, scheduling.getAgentSchedule()) && Objects.equals(day, scheduling.getDay()) && Objects.equals(month, scheduling.getMonth()) && Objects.equals(year, scheduling.getYear()) && Objects.equals(hour, scheduling.getHour() ))
                {
                    scheduling.setAcceptScheduling("rejectet");
                    scheduling.setRejection_message(Reason);
                    schedulingRepository.update(scheduling);
                    return;
                }

        }
            throw new SchedulingNotFoundException(Name, hour, day, month, year);

    }

}