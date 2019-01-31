import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Auth_logs } from 'app/shared/model/auth-logs.model';
import { Auth_logsService } from './auth-logs.service';
import { Auth_logsComponent } from './auth-logs.component';
import { Auth_logsDetailComponent } from './auth-logs-detail.component';
import { Auth_logsUpdateComponent } from './auth-logs-update.component';
import { Auth_logsDeletePopupComponent } from './auth-logs-delete-dialog.component';
import { IAuth_logs } from 'app/shared/model/auth-logs.model';

@Injectable({ providedIn: 'root' })
export class Auth_logsResolve implements Resolve<IAuth_logs> {
    constructor(private service: Auth_logsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Auth_logs> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Auth_logs>) => response.ok),
                map((auth_logs: HttpResponse<Auth_logs>) => auth_logs.body)
            );
        }
        return of(new Auth_logs());
    }
}

export const auth_logsRoute: Routes = [
    {
        path: 'auth-logs',
        component: Auth_logsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'myApp.auth_logs.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'auth-logs/:id/view',
        component: Auth_logsDetailComponent,
        resolve: {
            auth_logs: Auth_logsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'myApp.auth_logs.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'auth-logs/new',
        component: Auth_logsUpdateComponent,
        resolve: {
            auth_logs: Auth_logsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'myApp.auth_logs.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'auth-logs/:id/edit',
        component: Auth_logsUpdateComponent,
        resolve: {
            auth_logs: Auth_logsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'myApp.auth_logs.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const auth_logsPopupRoute: Routes = [
    {
        path: 'auth-logs/:id/delete',
        component: Auth_logsDeletePopupComponent,
        resolve: {
            auth_logs: Auth_logsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'myApp.auth_logs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
