
export class Roles{
  roleUser: boolean;
  roleEditor: boolean;
  roleSupport: boolean;
  roleModerator: boolean;
  roleAdmin: boolean;

  toString(): string{
    return 'roleUser: ' + this.roleUser + ', roleEditor: ' + this.roleEditor + ', roleSupport: ' + this.roleSupport +
           ', roleModerator: ' + this.roleModerator + ', roleAdmin: ' + this.roleAdmin;
  }
}
