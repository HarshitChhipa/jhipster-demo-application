import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Leads } from 'app/shared/model/leads.model';
import { LeadsService } from './leads.service';
import { LeadsComponent } from './leads.component';
import { LeadsDetailComponent } from './leads-detail.component';
import { LeadsUpdateComponent } from './leads-update.component';
import { LeadsDeletePopupComponent } from './leads-delete-dialog.component';
import { ILeads } from 'app/shared/model/leads.model';

@Injectable({ providedIn: 'root' })
export class LeadsResolve implements Resolve<ILeads> {
    constructor(private service: LeadsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Leads> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Leads>) => response.ok),
                map((leads: HttpResponse<Leads>) => leads.body)
            );
        }
        return of(new Leads());
    }
}

export const leadsRoute: Routes = [
    {
        path: 'leads',
        component: LeadsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'myApp.leads.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'leads/:id/view',
        component: LeadsDetailComponent,
        resolve: {
            leads: LeadsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'myApp.leads.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'leads/new',
        component: LeadsUpdateComponent,
        resolve: {
            leads: LeadsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'myApp.leads.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'leads/:id/edit',
        component: LeadsUpdateComponent,
        resolve: {
            leads: LeadsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'myApp.leads.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const leadsPopupRoute: Routes = [
    {
        path: 'leads/:id/delete',
        component: LeadsDeletePopupComponent,
        resolve: {
            leads: LeadsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'myApp.leads.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
