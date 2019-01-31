import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAuth_logs } from 'app/shared/model/auth-logs.model';

@Component({
    selector: 'jhi-auth-logs-detail',
    templateUrl: './auth-logs-detail.component.html'
})
export class Auth_logsDetailComponent implements OnInit {
    auth_logs: IAuth_logs;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ auth_logs }) => {
            this.auth_logs = auth_logs;
        });
    }

    previousState() {
        window.history.back();
    }
}
