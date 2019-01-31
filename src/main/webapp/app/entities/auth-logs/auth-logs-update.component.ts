import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAuth_logs } from 'app/shared/model/auth-logs.model';
import { Auth_logsService } from './auth-logs.service';

@Component({
    selector: 'jhi-auth-logs-update',
    templateUrl: './auth-logs-update.component.html'
})
export class Auth_logsUpdateComponent implements OnInit {
    auth_logs: IAuth_logs;
    isSaving: boolean;

    constructor(protected auth_logsService: Auth_logsService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ auth_logs }) => {
            this.auth_logs = auth_logs;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.auth_logs.id !== undefined) {
            this.subscribeToSaveResponse(this.auth_logsService.update(this.auth_logs));
        } else {
            this.subscribeToSaveResponse(this.auth_logsService.create(this.auth_logs));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAuth_logs>>) {
        result.subscribe((res: HttpResponse<IAuth_logs>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
