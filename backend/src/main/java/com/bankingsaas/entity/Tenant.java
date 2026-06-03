@Entity
@Table(name="tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tenantId;

    private String bankName;

    private String logoUrl;
}