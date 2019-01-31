import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAuth_logs } from 'app/shared/model/auth-logs.model';
import { Auth_logsService } from './auth-logs.service';

@Component({
    selector: 'jhi-auth-logs-delete-dialog',
    templateUrl: './auth-logs-delete-dialog.component.html'
})
export class Auth_logsDeleteDialogComponent {
    auth_logs: IAuth_logs;

    constructor(
        protected auth_logsService: Auth_logsService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.auth_logsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'auth_logsListModification',
                content: 'Deleted an auth_logs'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-auth-logs-delete-popup',
    template: ''
})
export class Auth_logsDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ auth_logs }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(Auth_logsDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.auth_logs = auth_logs;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
