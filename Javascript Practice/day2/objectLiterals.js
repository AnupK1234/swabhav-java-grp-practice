let employee = {
  f_name: "Anup",
  l_name: "Khismatrao",
  skills: ["Java", "C", "Javascript"],

  login: function () {
    console.log("LOGIN SUCCESS");
  },
  logout: function () {
    console.log("LOGOUT SUCCESS");
  },
  getSkills: function() {
    this?.skills.forEach((skill) => {
      console.log(skill);
    });
  }
};

console.log("Employee name is : " + employee.f_name + " " + employee.l_name);

employee.login();
employee.logout();
console.log("SKILLS ARE : ");

employee.getSkills();