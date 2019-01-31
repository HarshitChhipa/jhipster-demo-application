import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILeads } from 'app/shared/model/leads.model';

@Component({
    selector: 'jhi-leads-detail',
    templateUrl: './leads-detail.component.html'
})
export class LeadsDetailComponent implements OnInit {
    leads: ILeads;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ leads }) => {
            this.leads = leads;
        });
    }

    previousState() {
        window.history.back();
    }
}
