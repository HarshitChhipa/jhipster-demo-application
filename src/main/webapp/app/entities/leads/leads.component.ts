import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ILeads } from 'app/shared/model/leads.model';
import { AccountService } from 'app/core';
import { LeadsService } from './leads.service';

@Component({
    selector: 'jhi-leads',
    templateUrl: './leads.component.html'
})
export class LeadsComponent implements OnInit, OnDestroy {
    leads: ILeads[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected leadsService: LeadsService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.leadsService.query().subscribe(
            (res: HttpResponse<ILeads[]>) => {
                this.leads = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInLeads();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ILeads) {
        return item.id;
    }

    registerChangeInLeads() {
        this.eventSubscriber = this.eventManager.subscribe('leadsListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
