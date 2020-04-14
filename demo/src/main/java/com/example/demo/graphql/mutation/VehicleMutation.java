package com.example.demo.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.demo.graphql.dao.entity.Vehicle;
import com.example.demo.graphql.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class VehicleMutation implements GraphQLMutationResolver {

    @Autowired
    private VehicleService vehicleService;

    public Vehicle createVehicle(final String type, final String modelCode, final String brandName, final String launchDate) {
        return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }

    public String deleteVehicle(final int ID) {
        this.vehicleService.deleteVehicle(ID);
        return "Vehicle deleted";
    }

    public Vehicle updateVehicle(final int ID, final String type, final String modelCode, final String brandName, final String launchDate) {
        return this.vehicleService.updateVehicle(ID, type, modelCode, brandName, launchDate);
    }
}
