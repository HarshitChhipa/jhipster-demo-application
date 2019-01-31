import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyAppSharedModule } from 'app/shared';
import {
    Auth_logsComponent,
    Auth_logsDetailComponent,
    Auth_logsUpdateComponent,
    Auth_logsDeletePopupComponent,
    Auth_logsDeleteDialogComponent,
    auth_logsRoute,
    auth_logsPopupRoute
} from './';

const ENTITY_STATES = [...auth_logsRoute, ...auth_logsPopupRoute];

@NgModule({
    imports: [MyAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        Auth_logsComponent,
        Auth_logsDetailComponent,
        Auth_logsUpdateComponent,
        Auth_logsDeleteDialogComponent,
        Auth_logsDeletePopupComponent
    ],
    entryComponents: [Auth_logsComponent, Auth_logsUpdateComponent, Auth_logsDeleteDialogComponent, Auth_logsDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MyAppAuth_logsModule {}
