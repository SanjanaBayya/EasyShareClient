package com.cgi.easyshare.client;

//The object of this class is going to stored in ThreadLocal.
class UserDetails {
	private String userName;
	private String userType;

	public UserDetails(String userName, String userType) {
		this.userName = userName;
		this.userType = userType;
	}

	public String userName() {
		return this.userName;
	}
}

// A class with some methods that will fall in the hierarchy of method call a
// thread makes.
class SomeClass {
	public static void someMethod() {
		System.out.println("From SomeClass.someMethod: "
				+ ThreadLocalTest.getUserName());
		// Call to SomeOtherClass.someOtherMethod()
		SomeOtherClass.someOtherMethod();
	}

	public static void someOtherMethod() {
		System.out.println("From SomeClass.someOtherMethod: "
				+ ThreadLocalTest.getUserName());
	}
}

// Another class with some methods that will fall in the hierarchy of method
// call a thread makes.
class SomeOtherClass {
	public static void someMethod() {
		System.out.println("From SomeOtherClass.someMethod: "
				+ ThreadLocalTest.getUserName());
		// Call to SomeClass.someOtherMethod()
		SomeClass.someOtherMethod();
	}

	public static void someOtherMethod() {
		System.out.println("From SomeOtherClass.someOtherMethod: "
				+ ThreadLocalTest.getUserName());
	}
}

public class ThreadLocalTest {
	private static final ThreadLocal<UserDetails> accessingUser = new ThreadLocal<UserDetails>();

	public static String getUserName() {
		return accessingUser.get().userName();
	}

	public static void setUserDetails(UserDetails userDetails) {
		accessingUser.set(userDetails);
	}

	public static void main(String[] args) {
		// thread1 calls SomeClass.someMethod, which ends up calling
		// SomeOtherClass.someOtherMethod.
		// We create the user details object and set it as part of ThreadLocal,
		// using the method: setUserDetails.
		// The stored used details can be retrieved any where in the thread
		// execution flow just by calling: ThreadLocalTest.getUserName().
		Thread thread1 = new Thread() {
			public void run() {
				UserDetails u1 = new UserDetails("Anna", "ADMIN");
				new ThreadLocalTest().setUserDetails(u1);
				SomeClass.someMethod();
			}
		};
		thread1.run();
		// thread2 calls SomeOtherClass.someMethod, which ends up calling
		// SomeClass.someOtherMethod.
		// We create the user details object and set it as part of ThreadLocal,
		// using the method: setUserDetails.
		// The stored used details can be retrieved any where in the thread
		// execution flow just by calling: ThreadLocalTest.getUserName().
		Thread thread2 = new Thread() {
			public void run() {
				UserDetails u1 = new UserDetails("Hazare", "ADMIN");
				new ThreadLocalTest().setUserDetails(u1);
				SomeOtherClass.someMethod();
			}
		};
		thread2.run();
	}
}

