package com.amatiservice.rest.inter;

import com.amatiservice.dao.DaoInterface;
import com.amatiservice.dao.impl.DaoImpl;
import com.amatiservice.model.BranchModel;
import com.amatiservice.model.ProviderFatture;
import com.amatiservice.model.RecessedModel;
import com.amatiservice.model.UserModel;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.List;

@Path("/api")
public class ServiceImpl implements ServiceInterface {

    ObjectMapper mapper = new ObjectMapper();
    DaoInterface dao = new DaoImpl();

    public void initDb() throws Exception {
        try {
            this.dao.setupDatasource();
        } catch (Exception e) {
            System.out.println("Error inizializing grnDao " + e.getMessage());
            throw new Exception("Error inizializing grnDao " + e.getMessage(), e);
        }
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saveuser")
    public Response saveUser(UserModel userModel) {
        try {
            initDb();
            long userIndex = dao.createUser(userModel);
            return Response
                    .status(Response.Status.OK)
                    .entity(userIndex)
                    .status(200)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString())
                    .status(500)
                    .build();
        }

    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/retrievemodeluser")
    public Response retrieveModelUser() {
        UserModel userModel = new UserModel(1,"asd","asd","asd","asd");

        try {
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            String json = mapper.writeValueAsString(userModel);
            return Response
                    .status(Response.Status.OK)
                    .header("Result", "")
                    .entity(json)
                    .status(200)
                    .build();
        } catch (JsonProcessingException e) {

            return Response
                    .status(Response.Status.OK)
                    .entity(e.toString())
                    .status(500)
                    .build();
        }

    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/retrieverecessedmodel")
    public Response retrieveModelRecessed() {
        RecessedModel recessed = new RecessedModel(2,
                "description incasso",
                23.2,
                22,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                23);

        try {
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            String json = mapper.writeValueAsString(recessed);
            return Response
                    .status(Response.Status.OK)
                    .header("Result", "")
                    .entity(json)
                    .status(200)
                    .build();
        } catch (JsonProcessingException e) {

            return Response
                    .status(Response.Status.OK)
                    .entity(e.toString())
                    .status(500)
                    .build();
        }

    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/retrieveuserbyemail")
    public Response retrieveUserByEmail(UserModel userModelInput) {
        try {
            initDb();
            System.out.println("Retrieve user data by the following mail : " + userModelInput.getMail());
            UserModel userModel = dao.retrieveUserByEmail(userModelInput.getMail());
            return Response
                    .status(Response.Status.OK)
                    .entity(userModel)
                    .status(200)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString())
                    .status(500)
                    .build();
        }

    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createbranch")
    public Response createBranch(BranchModel branchModel) {

        System.out.println("Saving branch :" + branchModel.getName() + " with user mail :" + branchModel.geteMail());

        try {
            initDb();
            UserModel userModel = dao.retrieveUserByEmail(branchModel.geteMail());
            branchModel.setFkUserId(userModel.getId());
            long userIndex = dao.createBranch(branchModel);
            return Response
                    .status(Response.Status.OK)
                    .entity(userIndex)
                    .status(200)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/retrieverecessedbybranch")
    public Response retrieveRecessedByBranch(BranchModel branchModel) {

        System.out.println("Retrieve recessed by :" + branchModel.getName() + " with user mail :" + branchModel.geteMail() + " and id " + branchModel.getPkBranchId());

        try {
            initDb();
            List<RecessedModel> recessedModelsList = dao.retrieveRecessedByBranchId(branchModel.getPkBranchId());

            return Response
                    .status(Response.Status.OK)
                    .entity(recessedModelsList)
                    .status(200)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/retrievebranchesbyemail")
    public Response retrieveBranchByUserMail(UserModel userModel) {
        System.out.println("Retrieving branches by email :" + userModel.getMail());

        try {
            initDb();
            List<BranchModel> branchModelList = dao.retrieveBranchesByEmail(userModel.getMail());
            return Response
                    .status(Response.Status.OK)
                    .entity(branchModelList)
                    .status(200)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/saverecessed")
    public Response saveRecessed(RecessedModel recessedModel) {
        try {
            initDb();
            long userIndex = dao.createRecessedAmountForSpecificBranch(recessedModel);
            return Response
                    .status(Response.Status.OK)
                    .entity(userIndex)
                    .status(200)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.toString())
                    .status(500)
                    .build();
        }
    }

    @Override
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/retrievemodelbranch")
    public Response retrieveModelBranch() {
        BranchModel branchModel = new BranchModel(1,
                "branch",
                "amat@gmail.com",
                "123123123",
                "via del tormento 32",
                "4343234234",
                ProviderFatture.aruba,
                "XXXXXXXXXXXXXXXXX",
                "XXXXXXXXXXXXXXX",
                1);

        try {
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            String json = mapper.writeValueAsString(branchModel);
            return Response
                    .status(Response.Status.OK)
                    .header("Result", "")
                    .entity(json)
                    .status(200)
                    .build();
        } catch (JsonProcessingException e) {

            return Response
                    .status(Response.Status.OK)
                    .header("Result", "")
                    .entity(e.toString())
                    .status(500)
                    .build();
        }

    }

//    @Override
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/editUser")
//    public Response editUser(UserModel userModel) {
//        return Response
//                .status(Response.Status.OK)
//                .header("Result", "")
//                .entity("{JSON}")
//                .status(200)
//                .build();
//    }
//
//    @Override
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/deleteUser")
//    public Response deleteUser(UserModel userModel) {
//        return Response
//                .status(Response.Status.OK)
//                .header("Result", "")
//                .entity("{JSON}")
//                .status(200)
//                .build();
//    }
//
//    @Override
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/retrieveUserByEmail")
//    public Response retrieveUserByEmail(String email) {
//        return Response
//                .status(Response.Status.OK)
//                .header("Result", "")
//                .entity("{JSON}")
//                .status(200)
//                .build();
//    }
//
//    @Override
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/createBranch")
//    public Response createBranch(int userId, BranchModel branchModel) {
//        return Response
//                .status(Response.Status.OK)
//                .header("Result", "")
//                .entity("{JSON}")
//                .status(200)
//                .build();
//    }
//
//    @Override
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/retrieveUserByEmail")
//    public Response editBranch(BranchModel branchModel) {
//        return Response
//                .status(Response.Status.OK)
//                .header("Result", "")
//                .entity("{JSON}")
//                .status(200)
//                .build();
//    }
//
//    @Override
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/deleteBranch")
//    public Response deleteBranch(BranchModel branchModel) {
//        return Response
//                .status(Response.Status.OK)
//                .header("Result", "")
//                .entity("{JSON}")
//                .status(200)
//                .build();
//    }
//
//    @Override
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/retrieveBranchByUserMail")
//    public Response retrieveBranchByUserMail(int userId) {
//        return Response
//                .status(Response.Status.OK)
//                .header("Result", "")
//                .entity("{JSON}")
//                .status(200)
//                .build();
//    }
}
