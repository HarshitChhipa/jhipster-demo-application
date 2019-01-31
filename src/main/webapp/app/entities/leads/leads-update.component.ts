import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ILeads } from 'app/shared/model/leads.model';
import { LeadsService } from './leads.service';

@Component({
    selector: 'jhi-leads-update',
    templateUrl: './leads-update.component.html'
})
export class LeadsUpdateComponent implements OnInit {
    leads: ILeads;
    isSaving: boolean;

    constructor(protected leadsService: LeadsService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ leads }) => {
            this.leads = leads;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.leads.id !== undefined) {
            this.subscribeToSaveResponse(this.leadsService.update(this.leads));
        } else {
            this.subscribeToSaveResponse(this.leadsService.create(this.leads));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILeads>>) {
        result.subscribe((res: HttpResponse<ILeads>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
