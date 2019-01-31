import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MyAppSharedModule } from 'app/shared';
import {
    LeadsComponent,
    LeadsDetailComponent,
    LeadsUpdateComponent,
    LeadsDeletePopupComponent,
    LeadsDeleteDialogComponent,
    leadsRoute,
    leadsPopupRoute
} from './';

const ENTITY_STATES = [...leadsRoute, ...leadsPopupRoute];

@NgModule({
    imports: [MyAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [LeadsComponent, LeadsDetailComponent, LeadsUpdateComponent, LeadsDeleteDialogComponent, LeadsDeletePopupComponent],
    entryComponents: [LeadsComponent, LeadsUpdateComponent, LeadsDeleteDialogComponent, LeadsDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MyAppLeadsModule {}
