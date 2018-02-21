package com.youmeek.ssm;

import com.youmeek.ssm.module.user.mapper.PermissionMapper;
import com.youmeek.ssm.module.user.mapper.RoleMapper;
import com.youmeek.ssm.module.user.mapper.UserMapper;
import com.youmeek.ssm.module.user.pojo.Permission;
import com.youmeek.ssm.module.user.pojo.Role;
import com.youmeek.ssm.module.user.pojo.SysUser;
import com.youmeek.ssm.module.user.pojo.User;
import com.youmeek.ssm.module.user.realm.UserRealm;
import com.youmeek.ssm.module.user.service.PermissionService;
import com.youmeek.ssm.module.user.service.RoleService;
import com.youmeek.ssm.module.user.service.SysUserService;
import com.youmeek.ssm.module.user.service.UserService;
import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml","classpath*:spring/spring-shiro-web.xml"})
public class SSMTest {

//fuck
	@Autowired
	protected PermissionService permissionService;
	@Autowired
	protected RoleService roleService;
	@Autowired
	protected UserService userService;

	@Autowired
	private UserRealm userRealm;

	protected String password = "123";

	protected Permission p1;
	protected Permission p2;
	protected Permission p3;
	protected Role r1;
	protected Role r2;
	protected User u1;
	protected User u2;
	protected User u3;
	protected User u4;

	@Before
	public void setUp() {



		//1、新增权限
		p1 = new Permission("user:create", "用户模块新增", Boolean.TRUE);
		p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
		p3 = new Permission("menu:create", "菜单模块新增", Boolean.TRUE);
		permissionService.createPermission(p1);
		permissionService.createPermission(p2);
		permissionService.createPermission(p3);
		//2、新增角色
		r1 = new Role("admin", "管理员", Boolean.TRUE);
		r2 = new Role("user", "用户管理员", Boolean.TRUE);
		roleService.createRole(r1);
		roleService.createRole(r2);
		//3、关联角色-权限
		roleService.correlationPermissions(r1.getId(), p1.getId());
		roleService.correlationPermissions(r1.getId(), p2.getId());
		roleService.correlationPermissions(r1.getId(), p3.getId());

		roleService.correlationPermissions(r2.getId(), p1.getId());
		roleService.correlationPermissions(r2.getId(), p2.getId());

		//4、新增用户
		u1 = new User("zhang", password);
		u2 = new User("li", password);
		u3 = new User("wu", password);
		u4 = new User("wang", password);
		u4.setLocked(Boolean.TRUE);
		userService.createUser(u1);
		userService.createUser(u2);
		userService.createUser(u3);
		userService.createUser(u4);
		//5、关联用户-角色
		userService.correlationRoles(u1.getId(), r1.getId());

	}

	@Test
	public void test() {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("li", "123");
		subject.login(token);

		Assert.assertTrue(subject.isAuthenticated());
//		subject.checkRole("admin");
//		subject.checkPermission("user:create");
//
//		userService.changePassword(u1.getId(), password + "1");
//		userRealm.clearCache(subject.getPrincipals());
//
//		token = new UsernamePasswordToken(u1.getUsername(), password + "1");
//		subject.login(token);




	}
}
