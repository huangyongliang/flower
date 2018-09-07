/**
 * @author yryub25075
 * @date 2018/9/6
 **/
public class Test1 {
    public static void main(String[] args) {
        String string = "" +
                "creditId: String\n" +
                "fundOrg: String\n" +
                "loanAmt: BigDecimal\n" +
                "contractId: String\n" +
                "loanInvoiceId: String\n" +
                "loanUser: UserPojo\n" +
                "borrowUser: UserPojo\n" +
                "productInfoPojo: ProductInfoPojo\n" +
                "feeRuleList: List<FeeRulePojo>\n" +
                "discountRuleList: List<DiscountRulePojo>\n" +
                "receiveUser: UserPojo\n" +
                "isRightNow: EnumBool\n" +
                "loanCode: String\n" +
                "summary: String\n" +
                "withholdType: EnumBool\n" +
                "payMode: EnumPayMode\n" +
                "withholdSignDate: Date\n" +
                "partnerUserId: String\n" +
                "repayPlanList: List<RepaySynchronizationRepayPlanPojo>\n" +
                "flag: Boolean\n" +
                "merchantUserId: String\n" +
                "partnerFee: BigDecimal\n";
        //System.out.println(string);
        String[] fields =string.split("\n");
        for(int i = 0;i<fields.length;i++){
            System.out.println(fields[i].split(":")[0]);
        }
    }
}
