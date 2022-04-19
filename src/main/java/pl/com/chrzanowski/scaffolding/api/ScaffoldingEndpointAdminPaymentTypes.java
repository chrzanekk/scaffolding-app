package pl.com.chrzanowski.scaffolding.api;

import org.springframework.web.bind.annotation.*;
import pl.com.chrzanowski.scaffolding.api.paymenttypes.*;
import pl.com.chrzanowski.scaffolding.domain.paymenttypes.PaymentTypeData;
import pl.com.chrzanowski.scaffolding.domain.paymenttypes.PaymentTypeFilter;
import pl.com.chrzanowski.scaffolding.logic.IPaymentTypes;
import pl.com.chrzanowski.scaffolding.logic.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/scaffolding")
public class ScaffoldingEndpointAdminPaymentTypes {

    private IPaymentTypes paymentTypes;

    public ScaffoldingEndpointAdminPaymentTypes(IPaymentTypes paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    @GetMapping(path = "/payment-types", produces = "application/json; charset=UTF-8")
    public PaymentTypesRequestGetResponse paymentTypes(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<PaymentTypeData> paymentTypesList = paymentTypes.find(new PaymentTypeFilter(page, pageSize, false));
        return new PaymentTypesRequestGetResponse(paymentTypesToResponse(paymentTypesList));
    }

    @GetMapping(path = "/removed-payment-types", produces = "application/json; charset=UTF-8")
    public PaymentTypesRequestGetResponse removedPaymentTypes(
            @RequestParam(name = "page", required = false, defaultValue = "1") Long page,
            @RequestParam(name = "page_size", required = false, defaultValue = "10") Long pageSize) {
        List<PaymentTypeData> paymentTypesList = paymentTypes.find(new PaymentTypeFilter(page, pageSize, true));
        return new PaymentTypesRequestGetResponse(paymentTypesToResponse(paymentTypesList));
    }

    @GetMapping(path = "/payment-types/{id}", produces = "application/json; charset=UTF-8")
    public PaymentTypeRequestGetResponse paymentTypeById(@PathVariable Long id) {
        PaymentTypeData paymentTypeData = paymentTypes.find(new PaymentTypeFilter(id)).get(0);
        return new PaymentTypeRequestGetResponse(paymentTypeToResponse(paymentTypeData));
    }

    @PostMapping(path = "/payment-type", consumes = "application/json; charset=UTF-8")
    public void addPaymentType(@RequestBody PaymentTypePostRequest request) {
        paymentTypes.add(new PaymentTypeData(request.getName(), request.getCreateUserId()));
    }

    @PutMapping(path = "/payment-type/{id}", consumes = "application/json; charset=UTF-8")
    public void updatePaymentType(@PathVariable Long id,
                                  @RequestBody PaymentTypePutRequest request) {
        paymentTypes.update(new PaymentTypeData(id, request.getName(), request.getModifyUserId()));
    }

    @PutMapping(path = "/payment-type-to-remove/{id}", consumes = "application/json; charset=UTF-8")
    public void removePaymentType(@PathVariable Long id,
                                  @RequestBody PaymentTypePutRequest request) {
        paymentTypes.remove(new PaymentTypeData(id, request.getRemoveUserId(), request.getName()));
    }



    private List<PaymentTypeGetResponse> paymentTypesToResponse(List<PaymentTypeData> paymentTypes) {
        List<PaymentTypeGetResponse> list = new ArrayList<>();
        for (PaymentTypeData data : paymentTypes) {
            list.add(paymentTypeToResponse(data));
        }
        return list;
    }

    private PaymentTypeGetResponse paymentTypeToResponse(PaymentTypeData paymentType) {
        return new PaymentTypeGetResponse(
                paymentType.getId(),
                paymentType.getName(),
                DateUtil.parseDateTime(paymentType.getCreateDate()),
                DateUtil.parseDateTime(paymentType.getModifyDate()),
                DateUtil.parseDateTime(paymentType.getRemoveDate()),
                paymentType.getCreateUserId(),
                paymentType.getModifyUserId(),
                paymentType.getRemoveUserId()
        );
    }


}
