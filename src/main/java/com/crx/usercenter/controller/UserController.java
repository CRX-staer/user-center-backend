package com.crx.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crx.usercenter.common.BaseResponse;
import com.crx.usercenter.common.ErrorCode;
import com.crx.usercenter.common.ResultUtils;
import com.crx.usercenter.exception.BusinessException;
import com.crx.usercenter.model.domain.User;
import com.crx.usercenter.model.request.UserLoginRequest;
import com.crx.usercenter.model.request.UserRegisterRequest;
import com.crx.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.crx.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.crx.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author Cai Rongxin
 * @date 2022/12/15 - 20:12
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){//为了避免繁琐地获取请求参数，这里对请求体进行封装   @RequestBody:将请求体数据注入到修饰的参数中
        if(userRegisterRequest == null){
//            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        //不带逻辑进行校验
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        long id = userService.register(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(id);
    }
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){//为了避免繁琐地获取请求参数，这里对请求体进行封装   @RequestBody:将请求体数据注入到修饰的参数中
        if(userLoginRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        //不带逻辑进行校验
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout (HttpServletRequest request){
        if(request == null){
            return null;
        }
        int logout = userService.userLogout(request);
        return ResultUtils.success(logout);
    }

    @GetMapping("/current")
    public BaseResponse<User> getUserCurrent(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        //返回给前端一个最新的用户信息
        User user = userService.getById(userId);
        //并且要脱敏的信息
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }
    //思考一下，管理方法是不是只能由管理员有能使用 所有要鉴权

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username,HttpServletRequest request){
        if(!isAdmin(request)){
            throw new BusinessException(ErrorCode.NOT_AUTH,"您不是管理员");
        }
        //模糊查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(username != null){
            queryWrapper.like("username",username);
        }
        //这里查询到的数据有点多，得使用脱敏后的用户信息 ->把脱敏的代码抽取成方法供其他类调用
        List<User> userList = userService.list(queryWrapper);
        //遍历userList 把每个对象重新赋值为userService.getSafetyUser(user) 添加到userList中
        List<User> users = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(users);
    }

    @GetMapping("/show")
    public BaseResponse<List<User>> showUsers(HttpServletRequest request){
        if(!isAdmin(request)){
            throw new BusinessException(ErrorCode.NOT_AUTH,"您不是管理员");
        }
        //这里查询到的数据有点多，得使用脱敏后的用户信息 ->把脱敏的代码抽取成方法供其他类调用
        List<User> userList = userService.list();
        //遍历userList 把每个对象重新赋值为userService.getSafetyUser(user) 添加到userList中
        List<User> users = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(users);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody Long id, HttpServletRequest request){
        if(!isAdmin(request)){
            throw new BusinessException(ErrorCode.NOT_AUTH,"您不是管理员");
        }
        if(id <= 0){
            throw new BusinessException(ErrorCode.NOT_AUTH,"用户id不能小于0");
        }
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }

    //抽取用户权限检查代码
    public boolean isAdmin(HttpServletRequest request){
        //需要用到用户的登录态 USER_LOGIN_STATE 把userServiceImp常量抽取成常量类
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
//        if(user == null || user.getUserRole() != ADMIN_ROLE){
//            return false;
//        }
//        return true;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }

}
