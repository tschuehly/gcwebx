import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthenticationService} from './authentication.service';
import {Roles} from '../model/roles';
import {timeout} from 'rxjs/operators';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private authenticationService: AuthenticationService, private router: Router) {
  }
  private currentRole: Roles;

  /**
   *  Protects the routes to reach with authentication
   */

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): any {
    const adminRoutes: string[] = ['/edit', '/memberTable', '/userTable', '/esport/teammanagement', '/esport/matchmanagement'];
    const modRoutes: string[] = ['/edit', '/memberTable', '/esport/teammanagement', '/esport/matchmanagement'];

    this.currentRole = this.authenticationService.currentRoles$.getValue();
    if (this.currentRole){
      if (this.currentRole.roleAdmin && (adminRoutes.includes(state.url))) {
        return true;
      }else if (this.currentRole.roleModerator && (modRoutes.includes(state.url))){

      }else {
        return false;
      }
    }else {
      return false;
    }
  }

}
